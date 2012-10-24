package com.greatmancode.javaserver;

import java.util.ArrayList;
import java.util.List;

import com.greatmancode.javaserver.net.User;
import com.greatmancode.javaserver.net.codecs.ChannelJoinCodec;
import com.greatmancode.javaserver.net.codecs.ChannelPartCodec;
import com.greatmancode.javaserver.net.codecs.ChannelQuitCodec;
import com.greatmancode.javaserver.net.codecs.JoinCodec;
import com.greatmancode.javaserver.net.codecs.KickCodec;
import com.greatmancode.javaserver.net.codecs.NamesCodec;
import com.greatmancode.javaserver.net.codecs.NamesEndCodec;
import com.greatmancode.javaserver.net.codecs.NoTopicCodec;
import com.greatmancode.javaserver.net.codecs.PrivMsgCodec;
import com.greatmancode.javaserver.net.codecs.TopicCodec;

public class Channel {

	private ArrayList<User> userList = new ArrayList<User>();
	private List<User> opList = new ArrayList<User>();
	private final String name;
	private String topic, modes = "+nt";

	public Channel(String name) {
		this.name = name;
	}

	public String getModes() {
		return modes;
	}

	public void addOp(User conn) {
		if (!opList.contains(conn)) {
			opList.add(conn);
		}
	}

	public List<User> getOpList() {
		return opList;
	}

	public void addUser(User conn) {
		if (userList.contains(conn)) {
			return;
		}
		conn.send(new JoinCodec(conn, name));
		// conn.send(new ModeCodec(name, modes));
		conn.send(new NoTopicCodec(conn, name));

		for (User users : userList) {
			users.send(new ChannelJoinCodec(conn, this));
		}
		userList.add(conn);
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
		for (User user : userList) {
			user.send(new KickCodec(kicker, kicked, this, reason));
		}
		userList.remove(kicked);
	}

	public List<User> getUserList() {
		return userList;
	}

	public void sendMessage(User conn, String[] message) {
		String msg = "";
		for (int i = 0; i < message.length; i++) {
			msg += message[i] + " ";
		}
		for (User chanList : userList) {
			if (!chanList.equals(conn)) {
				chanList.send(new PrivMsgCodec(conn, this, msg));
			}

		}
	}

	public void removeUser(User connection, boolean disconnect) {
		if (userList.contains(connection)) {
			userList.remove(connection);
		}
		if (!disconnect) {
			connection.send(new ChannelPartCodec(connection, this));
		} else {
			connection.send(new ChannelQuitCodec(connection, this));
		}
		for (User users : userList) {
			if (disconnect) {
				users.send(new ChannelQuitCodec(connection, this));
			} else {
				users.send(new ChannelPartCodec(connection, this));
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
		for (User conn : userList) {
			conn.send(new TopicCodec(modifier, this));
		}
	}
	
	public String getTopic() {
		return topic;
	}
}
