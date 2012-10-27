package com.greatmancode.javaserver.channel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.greatmancode.javaserver.Server;
import com.greatmancode.javaserver.net.codecs.ChannelJoinCodec;
import com.greatmancode.javaserver.net.codecs.ChannelPartCodec;
import com.greatmancode.javaserver.net.codecs.ChannelQuitCodec;
import com.greatmancode.javaserver.net.codecs.JoinCodec;
import com.greatmancode.javaserver.net.codecs.KickCodec;
import com.greatmancode.javaserver.net.codecs.ModeUserChannelCodec;
import com.greatmancode.javaserver.net.codecs.NamesCodec;
import com.greatmancode.javaserver.net.codecs.NamesEndCodec;
import com.greatmancode.javaserver.net.codecs.NoTopicCodec;
import com.greatmancode.javaserver.net.codecs.PrivMsgCodec;
import com.greatmancode.javaserver.net.codecs.TopicCodec;
import com.greatmancode.javaserver.user.User;

/**
 * Represents a channel.
 * @author greatman
 *
 */
//TODO: Support mode chan modes.
//TODO: Support banning.
//TODO: Support voice
public class Channel {

	private Map<User, ChannelUser> userList = new HashMap<User, ChannelUser>();
	private final String name;
	private String topic, modes = "+nt";

	public Channel(String name) {
		this.name = name;
	}

	/**
	 * Retrieve the channel modes.
	 * @return The channel modes.
	 */
	public String getModes() {
		return modes;
	}

	/**
	 * Add a mode to a user in the channel.
	 * @param changer The user that is changing the mode. If the server set to null.
	 * @param user The user that is being changed.
	 * @param mode The mode we want to add to the player.
	 */
	public void addUserMode(User changer, User user, ChannelUserModes mode) {
		if (userList.containsKey(user)) {
			ChannelUser chanUser = userList.get(user);
			if (!chanUser.getUserModes().contains(mode)) {
				chanUser.getUserModes().add(mode);
				Iterator<User> iterator = userList.keySet().iterator();
				while (iterator.hasNext()) {
					iterator.next().send(new ModeUserChannelCodec(changer, user, this, mode, true));
				}
			}
		}
	}

	/**
	 * Remove a mode to a user in the channel.
	 * @param changer The user that is changing the mode. If the server set to null.
	 * @param user The user that is being changed.
	 * @param mode The mode we want to remove to the player.
	 */
	public void removeUserMode(User changer, User user, ChannelUserModes mode) {
		if (userList.containsKey(user)) {
			ChannelUser chanUser = userList.get(user);
			if (chanUser.getUserModes().contains(mode)) {
				chanUser.getUserModes().remove(mode);
				Iterator<User> iterator = userList.keySet().iterator();
				while (iterator.hasNext()) {
					iterator.next().send(new ModeUserChannelCodec(changer, user, this, mode, false));
				}
			}
		}
	}

	/**
	 * Add a user in the channel.
	 * @param user The user to add
	 */
	public void addUser(User user) {
		if (userList.containsKey(user)) {
			return;
		}
		user.send(new JoinCodec(user, name));
		user.send(new NoTopicCodec(user, name));

		Iterator<User> iterator = userList.keySet().iterator();
		while (iterator.hasNext()) {
			iterator.next().send(new ChannelJoinCodec(user, this));
		}
		ChannelUser chanUser = new ChannelUser();
		if (userList.size() == 0) {
			chanUser.getUserModes().add(ChannelUserModes.OP);
		}
		userList.put(user, chanUser);

		user.send(new NamesCodec(user, this));
		user.send(new NamesEndCodec(user, this));
	}

	/**
	 * Retrieve the name of the channel. Example: #lobby
	 * @return The name of the channel.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Kick a player from the channel
	 * @param kicker The kicker.
 	 * @param kicked The kicked.
	 */
	public void kickUser(User kicker, User kicked) {
		kickUser(kicker, kicked, kicked.getNickname());
	}

	/**
	 * Kick a player from the channel
	 * @param kicker The kicker.
 	 * @param kicked The kicked.
 	 * @param reason The reason of the kick
	 */
	public void kickUser(User kicker, User kicked, String reason) {
		Iterator<User> iterator = userList.keySet().iterator();
		while (iterator.hasNext()) {
			iterator.next().send(new KickCodec(kicker, kicked, this, reason));
		}
		userList.remove(kicked);
	}

	/**
	 * Retrieve a list of all the users in the channel.
	 * @return The channel user list.
	 */
	public Map<User, ChannelUser> getUserList() {
		return userList;
	}

	/**
	 * Send a message to all the users in the channel
	 * @param user The user that sent the message.
	 * @param message The message to send to all the users.
	 */
	public void sendMessage(User user, String message) {
		Iterator<User> iterator = userList.keySet().iterator();
		while (iterator.hasNext()) {
			User receiver = iterator.next();
			if (!receiver.equals(user)) {
				receiver.send(new PrivMsgCodec(user, this, message));
			}

		}
	}

	/**
	 * Remove a user from the channel.
	 * @param user The user to remove
	 * @param disconnect If the user is disconnecting or not.
	 */
	public void removeUser(User user, boolean disconnect) {
		if (userList.containsKey(user)) {
			userList.remove(user);
		}
		if (!disconnect) {
			user.send(new ChannelPartCodec(user, this));
		} else {
			user.send(new ChannelQuitCodec(user, this));
		}
		Iterator<User> iterator = userList.keySet().iterator();
		while (iterator.hasNext()) {
			if (disconnect) {
				iterator.next().send(new ChannelQuitCodec(user, this));
			} else {
				iterator.next().send(new ChannelPartCodec(user, this));
			}

		}
		if (userList.size() == 0 && Server.CHANNEL_LIST.containsKey(name)) {
			Server.CHANNEL_LIST.remove(name);
		}

	}

	/**
	 * Set the topic of this channel.
	 * @param user The user that modified the topic. Null for server
	 * @param topic
	 */
	//TODO: Support server
	public void setTopic(User user, String topic) {
		this.topic = topic;
		Iterator<User> iterator = userList.keySet().iterator();
		while (iterator.hasNext()) {
			iterator.next().send(new TopicCodec(user, this));
		}
	}

	/**
	 * Retrieve the topic of the channel.
	 * @return The topic of the channel.
	 */
	public String getTopic() {
		return topic;
	}
}
