/*
 * This file is part of javaserver.
 *
 * Copyright (c) 2011-2012,
 * 							${project.organization.name} <${url}/>
 *
 * javaserver is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * javaserver is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with javaserver.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.greatmancode.javaserver.channel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.greatmancode.javaserver.Server;
import com.greatmancode.javaserver.event.Source;
import com.greatmancode.javaserver.event.events.channel.ChannelModeChangeEvent;
import com.greatmancode.javaserver.event.events.channel.ChannelTopicChangeEvent;
import com.greatmancode.javaserver.event.events.channel.ChannelUserModeChangeEvent;
import com.greatmancode.javaserver.event.events.channel.ChannelUserQuitEvent;
import com.greatmancode.javaserver.event.events.channel.UserChannelKickEvent;
import com.greatmancode.javaserver.event.events.channel.UserChannelMessageEvent;
import com.greatmancode.javaserver.event.events.channel.UserJoinChannelEvent;
import com.greatmancode.javaserver.net.codecs.ChannelJoinCodec;
import com.greatmancode.javaserver.net.codecs.ChannelPartCodec;
import com.greatmancode.javaserver.net.codecs.JoinCodec;
import com.greatmancode.javaserver.net.codecs.KickCodec;
import com.greatmancode.javaserver.net.codecs.ModeChannelChangeCodec;
import com.greatmancode.javaserver.net.codecs.ModeUserChannelCodec;
import com.greatmancode.javaserver.net.codecs.NamesCodec;
import com.greatmancode.javaserver.net.codecs.NamesEndCodec;
import com.greatmancode.javaserver.net.codecs.NoTopicCodec;
import com.greatmancode.javaserver.net.codecs.PrivMsgCodec;
import com.greatmancode.javaserver.net.codecs.QuitCodec;
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
	private String topic = "";
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

	public void changeMode(Source source, List<ChannelMode> modes, boolean add) {
		if (modes != null) {
			ChannelModeChangeEvent event = (ChannelModeChangeEvent) Server.getServer().getEventManager().callEvent(new ChannelModeChangeEvent(source, this, modes, add));
			if (!event.isCancelled()) {
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
						iterator2.next().send(new ModeChannelChangeCodec(event.getSource(), this, modes, add));
					}
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
	public void changeUserMode(Source changer, User user, ChannelUserMode mode, boolean add) {
		ChannelUser chanUser = userList.get(user);
		if (chanUser != null) {
			if (add) {
				if (!chanUser.getUserModes().contains(mode)) {
					ChannelUserModeChangeEvent event = (ChannelUserModeChangeEvent) Server.getServer().getEventManager().callEvent(new ChannelUserModeChangeEvent(changer, user, this, mode, add));
					if (!event.isCancelled()) {
						chanUser.getUserModes().add(mode);
						Iterator<User> iterator = userList.keySet().iterator();
						while (iterator.hasNext()) {
							iterator.next().send(new ModeUserChannelCodec(changer, user, this, mode, true));
						}
					}
				}
				
			} else {
				ChannelUserModeChangeEvent event = (ChannelUserModeChangeEvent) Server.getServer().getEventManager().callEvent(new ChannelUserModeChangeEvent(changer, user, this, mode, add));
				if (!event.isCancelled()) {
					chanUser.getUserModes().remove(mode);
					Iterator<User> iterator = userList.keySet().iterator();
					while (iterator.hasNext()) {
						iterator.next().send(new ModeUserChannelCodec(changer, user, this, mode, false));
					}
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

		ChannelUser chanUser = new ChannelUser();
		boolean first = false;
		if (userList.size() == 0) {
			chanUser.getUserModes().add(ChannelUserMode.OP);
			first = true;
		}

		UserJoinChannelEvent event = (UserJoinChannelEvent) Server.getServer().getEventManager().callEvent(new UserJoinChannelEvent(user, this, chanUser));
		if (!event.isCancelled()) {
			if (!first) {
				Iterator<User> iterator = userList.keySet().iterator();
				while (iterator.hasNext()) {
					iterator.next().send(new ChannelJoinCodec(user, this));
				}
			}

			user.send(new JoinCodec(user, name));
			user.send(new NoTopicCodec(user, name));

			userList.put(user, chanUser);

			user.send(new NamesCodec(user, this));
			user.send(new NamesEndCodec(user, this));
			
		} else if (first) {
			Server.getServer().getChannelHandler().removeChannel(name);
		}

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
	public void kickUser(Source kicker, User kicked) {
		kickUser(kicker, kicked, kicked.getNickname());
	}

	/**
	 * Kick a player from the channel
	 * 
	 * @param kicker The kicker.
	 * @param kicked The kicked.
	 * @param reason The reason of the kick
	 */
	public void kickUser(Source kicker, User kicked, String reason) {
		UserChannelKickEvent event = (UserChannelKickEvent) Server.getServer().getEventManager().callEvent(new UserChannelKickEvent(kicker, kicked, this, reason));
		if (!event.isCancelled()) {
			Iterator<User> iterator = userList.keySet().iterator();
			while (iterator.hasNext()) {
				iterator.next().send(new KickCodec(kicker, kicked, this, reason));
			}
			removeUser(kicked, ChannelQuitReasons.KICKED);
		}
		
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
				} else if (reason.equals(ChannelQuitReasons.DISCONNECT)) {
					user.send(new QuitCodec(user));
				}

				if (reason.equals(ChannelQuitReasons.PART) || reason.equals(ChannelQuitReasons.DISCONNECT)) {
					Iterator<User> iterator = userList.keySet().iterator();
					while (iterator.hasNext()) {
						if (reason.equals(ChannelQuitReasons.PART)) {
							iterator.next().send(new ChannelPartCodec(user, this));

						} else if (reason.equals(ChannelQuitReasons.DISCONNECT)) {
							iterator.next().send(new QuitCodec(user));
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
	 * @param source The user that modified the topic. Null for server
	 * @param topic
	 */
	// TODO: Support server
	public void setTopic(Source source, String topic) {
		ChannelTopicChangeEvent event = (ChannelTopicChangeEvent) Server.getServer().getEventManager().callEvent(new ChannelTopicChangeEvent(this, source, topic));
		if (!event.isCancelled()) {
			if (topic == null ){
				this.topic = "";
			} else {
				this.topic = topic;
			}
			Iterator<User> iterator = userList.keySet().iterator();
			while (iterator.hasNext()) {
				iterator.next().send(new TopicCodec(source, this));
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
