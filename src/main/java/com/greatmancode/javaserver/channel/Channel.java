package com.greatmancode.javaserver.channel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.greatmancode.javaserver.Server;
import com.greatmancode.javaserver.event.events.ChannelTopicChangeEvent;
import com.greatmancode.javaserver.event.events.ChannelUserQuitEvent;
import com.greatmancode.javaserver.event.events.UserChannelMessageEvent;
import com.greatmancode.javaserver.net.codecs.ChannelJoinCodec;
import com.greatmancode.javaserver.net.codecs.ChannelPartCodec;
import com.greatmancode.javaserver.net.codecs.ChannelQuitCodec;
import com.greatmancode.javaserver.net.codecs.JoinCodec;
import com.greatmancode.javaserver.net.codecs.KickCodec;
import com.greatmancode.javaserver.net.codecs.ModeChannelChangeCodec;
import com.greatmancode.javaserver.net.codecs.ModeUserChannelCodec;
import com.greatmancode.javaserver.net.codecs.NamesCodec;
import com.greatmancode.javaserver.net.codecs.NamesEndCodec;
import com.greatmancode.javaserver.net.codecs.NoTopicCodec;
import com.greatmancode.javaserver.net.codecs.PrivMsgCodec;
import com.greatmancode.javaserver.net.codecs.TopicCodec;
import com.greatmancode.javaserver.user.User;

/**
 * Represents a channel.
 * 
 * @author greatman
 * 
 */
// TODO: Support mode chan modes. Implement actual usage of them. +m works.
// TODO: Support banning.
public class Channel {

	private Map<User, ChannelUser> userList = new HashMap<User, ChannelUser>();
	private final String name;
	private String topic;
	private List<ChannelMode> chanModes = new ArrayList<ChannelMode>();

	public Channel(String name) {
		this.name = name;
		chanModes.add(ChannelMode.NO_EXTERNAL_MESSAGES);
		chanModes.add(ChannelMode.TOPIC_LOCK);
	}

	/**
	 * Retrieve the channel modes. This is a COPY of the current modes.
	 * 
	 * @return The channel modes.
	 */
	public List<ChannelMode> getModes() {
		List<ChannelMode> modes = new ArrayList<ChannelMode>();
		for (ChannelMode mode : chanModes) {
			modes.add(mode);
		}
		return modes;
	}

	public void removeMode(UserMode mode) {
		chanModes.remove(mode);
	}

	public void changeMode(User user, List<ChannelMode> modes, boolean add) {
		if (modes != null) {
			if (user != null) {
				if (!userList.get(user).getUserModes().contains(ChannelUserMode.OP)) {
					return;
				}
			}
			if (add) {
				Iterator<ChannelMode> iterator = modes.iterator();
				while (iterator.hasNext()) {
					ChannelMode mode = iterator.next();
					if (chanModes.contains(mode)) {
						iterator.remove();
					} else {
						chanModes.add(mode);
					}
				}
			} else {
				Iterator<ChannelMode> iterator = modes.iterator();
				while (iterator.hasNext()) {
					ChannelMode mode = iterator.next();
					if (!chanModes.contains(mode)) {
						iterator.remove();
					}
				}
			}
			
			if (modes.size() != 0) {
				Iterator<User> iterator2 = userList.keySet().iterator();
				while (iterator2.hasNext()) {
					iterator2.next().send(new ModeChannelChangeCodec(user, this, modes, add));
				}
			}

		}

	}

	public void changeMode(User user, ChannelMode mode, boolean add) {
		if (mode != null) {
			List<ChannelMode> modes = new ArrayList<ChannelMode>();
			modes.add(mode);
			changeMode(user, modes, add);
		}
	}

	/**
	 * Add a mode to a user in the channel.
	 * 
	 * @param changer The user that is changing the mode. If the server set to null.
	 * @param user The user that is being changed.
	 * @param mode The mode we want to add to the player.
	 */
	public void addUserMode(User changer, User user, ChannelUserMode mode) {
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
	 * 
	 * @param changer The user that is changing the mode. If the server set to null.
	 * @param user The user that is being changed.
	 * @param mode The mode we want to remove to the player.
	 */
	public void removeUserMode(User changer, User user, ChannelUserMode mode) {
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
	 * 
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
			chanUser.getUserModes().add(ChannelUserMode.OP);
		}
		userList.put(user, chanUser);

		user.send(new NamesCodec(user, this));
		user.send(new NamesEndCodec(user, this));
	}

	/**
	 * Retrieve the name of the channel. Example: #lobby
	 * 
	 * @return The name of the channel.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Kick a player from the channel
	 * 
	 * @param kicker The kicker.
	 * @param kicked The kicked.
	 */
	public void kickUser(User kicker, User kicked) {
		kickUser(kicker, kicked, kicked.getNickname());
	}

	/**
	 * Kick a player from the channel
	 * 
	 * @param kicker The kicker.
	 * @param kicked The kicked.
	 * @param reason The reason of the kick
	 */
	public void kickUser(User kicker, User kicked, String reason) {
		Iterator<User> iterator = userList.keySet().iterator();
		while (iterator.hasNext()) {
			iterator.next().send(new KickCodec(kicker, kicked, this, reason));
		}
		removeUser(kicked, ChannelQuitReasons.KICKED);
	}

	/**
	 * Retrieve a list of all the users in the channel.
	 * 
	 * @return The channel user list.
	 */
	public Map<User, ChannelUser> getUserList() {
		return userList;
	}

	/**
	 * Send a message to all the users in the channel
	 * 
	 * @param user The user that sent the message.
	 * @param message The message to send to all the users.
	 */
	public void sendMessage(User user, String message) {
		UserChannelMessageEvent event = (UserChannelMessageEvent) Server.getServer().getEventManager().callEvent(new UserChannelMessageEvent(user, this, message));
		if (!event.isCancelled()) {
			Iterator<User> iterator = userList.keySet().iterator();
			while (iterator.hasNext()) {
				User receiver = iterator.next();
				if (!receiver.equals(user)) {
					receiver.send(new PrivMsgCodec(user, this, message));
				}

			}
		}
		
	}

	/**
	 * Remove a user from the channel.
	 * 
	 * @param user The user to remove
	 * @param disconnect If the user is disconnecting or not.
	 */
	public void removeUser(User user, ChannelQuitReasons reason) {
		if (user != null && reason != null) {
			Server.getServer().getEventManager().callEvent(new ChannelUserQuitEvent(user, this, reason));
			if (userList.containsKey(user)) {
				userList.remove(user);
				if (reason.equals(ChannelQuitReasons.PART)) {
					user.send(new ChannelPartCodec(user, this));
				} else if (reason.equals(ChannelQuitReasons.DISCONNECT)){
					user.send(new ChannelQuitCodec(user, this));
				}
				
				if (reason.equals(ChannelQuitReasons.PART) || reason.equals(ChannelQuitReasons.DISCONNECT)) {
					Iterator<User> iterator = userList.keySet().iterator();
					while (iterator.hasNext()) {
						if (reason.equals(ChannelQuitReasons.PART)) {
							iterator.next().send(new ChannelPartCodec(user, this));
							
						} else if (reason.equals(ChannelQuitReasons.DISCONNECT)) {
							iterator.next().send(new ChannelQuitCodec(user, this));
						}

					}
				}
			}
			
			if (userList.size() == 0 && Server.getServer().getChannelHandler().getChannel(name) != null) {
				Server.getServer().getChannelHandler().removeChannel(name);
			}
		}
	}

	/**
	 * Set the topic of this channel.
	 * 
	 * @param user The user that modified the topic. Null for server
	 * @param topic
	 */
	// TODO: Support server
	public void setTopic(User user, String topic) {
		ChannelTopicChangeEvent event = (ChannelTopicChangeEvent) Server.getServer().getEventManager().callEvent(new ChannelTopicChangeEvent(this, user, topic));
		if (!event.isCancelled()) {
			this.topic = topic;
			Iterator<User> iterator = userList.keySet().iterator();
			while (iterator.hasNext()) {
				iterator.next().send(new TopicCodec(user, this));
			}
		}
		
	}

	/**
	 * Retrieve the topic of the channel.
	 * 
	 * @return The topic of the channel.
	 */
	public String getTopic() {
		return topic;
	}
}
