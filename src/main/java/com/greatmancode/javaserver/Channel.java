package com.greatmancode.javaserver;

import java.util.HashMap;
import java.util.Iterator;

import com.greatmancode.javaserver.net.User;
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

public class Channel {

	private HashMap<User, ChannelUser> userList = new HashMap<User,ChannelUser>();
	private final String name;
	private String topic, modes = "+nt";

	public Channel(String name) {
		this.name = name;
	}

	public String getModes() {
		return modes;
	}
	
	public void addOp(User changer, User user) {
		if (userList.containsKey(user)) {
			ChannelUser chanUser = userList.get(user);
			if (!chanUser.getUserModes().contains(ChannelUserModes.OP)) {
				System.out.println("Adding OP");
				chanUser.getUserModes().add(ChannelUserModes.OP);
				Iterator<User> iterator = userList.keySet().iterator();
				while (iterator.hasNext()) {
					iterator.next().send(new ModeUserChannelCodec(changer, user, this, ChannelUserModes.OP, true));
				}
			}
		}
	}

	public void addUser(User conn) {
		if (userList.containsKey(conn)) {
			return;
		}
		conn.send(new JoinCodec(conn, name));
		// conn.send(new ModeCodec(name, modes));
		conn.send(new NoTopicCodec(conn, name));

		Iterator<User> iterator = userList.keySet().iterator();
		while (iterator.hasNext()) {
			iterator.next().send(new ChannelJoinCodec(conn, this));
		}
		ChannelUser chanUser = new ChannelUser();
		if (userList.size() == 0) {
			chanUser.getUserModes().add(ChannelUserModes.OP);
		}
		userList.put(conn, chanUser);
		
		conn.send(new NamesCodec(conn, this));
		conn.send(new NamesEndCodec(conn, this));
	}

	public String getName() {
		return name;
	}

	public void KickUser(User kicker, User kicked) {
		kickUser(kicker, kicked, kicked.getNickname());
	}

	public void kickUser(User kicker, User kicked, String reason) {
		Iterator<User> iterator = userList.keySet().iterator();
		while (iterator.hasNext()) {
			iterator.next().send(new KickCodec(kicker, kicked, this, reason));
		}
		userList.remove(kicked);
	}

	public HashMap<User, ChannelUser> getUserList() {
		return userList;
	}

	public void sendMessage(User conn, String[] message) {
		String msg = "";
		for (int i = 0; i < message.length; i++) {
			msg += message[i] + " ";
		}
		Iterator<User> iterator = userList.keySet().iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if (!user.equals(conn)) {
				user.send(new PrivMsgCodec(conn, this, msg));
			}

		}
	}

	public void removeUser(User connection, boolean disconnect) {
		if (userList.containsKey(connection)) {
			userList.remove(connection);
		}
		if (!disconnect) {
			connection.send(new ChannelPartCodec(connection, this));
		} else {
			connection.send(new ChannelQuitCodec(connection, this));
		}
		Iterator<User> iterator = userList.keySet().iterator();
		while (iterator.hasNext()) {
			if (disconnect) {
				iterator.next().send(new ChannelQuitCodec(connection, this));
			} else {
				iterator.next().send(new ChannelPartCodec(connection, this));
			}

		}
		if (userList.size() == 0) {
			if (App.channelList.containsKey(name)) {
				App.channelList.remove(name);
			}
		}

	}

	public void setTopic(User modifier, String topic) {
		this.topic = topic;
		Iterator<User> iterator = userList.keySet().iterator();
		while (iterator.hasNext()) {
			iterator.next().send(new TopicCodec(modifier, this));
		}
	}
	
	public String getTopic() {
		return topic;
	}
}
