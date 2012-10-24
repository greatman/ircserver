package com.greatmancode.javaserver;

import java.util.ArrayList;
import java.util.List;

import com.greatmancode.javaserver.net.Connection;
import com.greatmancode.javaserver.net.codecs.ChannelJoinCodec;
import com.greatmancode.javaserver.net.codecs.ChannelPartCodec;
import com.greatmancode.javaserver.net.codecs.ChannelQuitCodec;
import com.greatmancode.javaserver.net.codecs.JoinCodec;
import com.greatmancode.javaserver.net.codecs.ModeCodec;
import com.greatmancode.javaserver.net.codecs.NamesCodec;
import com.greatmancode.javaserver.net.codecs.NamesEndCodec;
import com.greatmancode.javaserver.net.codecs.NoTopicCodec;
import com.greatmancode.javaserver.net.codecs.PrivMsgCodec;

public class Channel {

	private List<Connection> userList = new ArrayList<Connection>();
	private final String name;
	private String topic, modes = "+nt";

	public Channel(String name) {
		this.name = name;
	}

	public String getModes() {
		return modes;
	}

	public void addUser(Connection conn) {
		if (userList.contains(conn)) {
			return;
		}
		userList.add(conn);
		conn.send(new JoinCodec(conn, name));
		conn.send(new ModeCodec(conn, name, modes));
		conn.send(new NoTopicCodec(conn, name));
		for (Connection channelMember : this.userList) {// 353,366

			conn.send(new NamesCodec(conn, this, channelMember));
		}
		conn.send(new NamesEndCodec(conn, this));
		for (Connection users : userList) {
			users.send(new ChannelJoinCodec(conn, this));
		}
	}

	public String getName() {
		return name;
	}

	public void kickUser(String username) {

	}

	public List<Connection> getUserList() {
		return userList;
	}

	public void sendMessage(Connection conn, String[] message) {
		String msg = "";
		for (int i = 0; i < message.length; i++) {
			msg += message[i] + " ";
		}
		for (Connection chanList : userList) {
			if (!chanList.equals(conn)) {
				chanList.send(new PrivMsgCodec(conn, this, msg));
			}

		}
	}

	public void removeUser(Connection connection, boolean disconnect) {
		if (userList.contains(connection)) {
			userList.remove(connection);
		}
		if (!disconnect) {
			connection.send(new ChannelPartCodec(connection, this));
		}
		for (Connection users : userList) {
			if (disconnect) {
				users.send(new ChannelQuitCodec(connection, this));
			}
			else {
				users.send(new ChannelPartCodec(connection, this));
			}
			
		}
		
		
		
	}
}
