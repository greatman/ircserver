package com.greatmancode.javaserver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.greatmancode.javaserver.net.Connection;
import com.greatmancode.javaserver.net.codecs.JoinCodec;
import com.greatmancode.javaserver.net.codecs.ModeCodec;
import com.greatmancode.javaserver.net.codecs.NoTopicCodec;

public class Channel {

	private List<Connection> userList = new ArrayList<Connection>();
	private String name;
	private String topic;
	
	public Channel(String name) {
		this.name = name;
	}
	public void addUser(Connection conn) {
		if (userList.contains(conn)) {
			return;
		}
		userList.add(conn);
		conn.send(new JoinCodec(conn, name));
		conn.send(new ModeCodec(conn, name, "+nt"));
		conn.send(new NoTopicCodec(conn, name));
		for (Connection channelMember : this.userList)
        {// 353,366
            conn.send(":test 353 " + conn.getNickname() + " = " + name
                    + " :" + channelMember.getNickname());
        }
		conn.send(":test 366 " + conn.getNickname() + " " + name + " : End of /NAMES list");
	}
	
	public void kickUser(String username) {
		
	}
	
	public List<Connection> getUserList() {
		return userList;
	}
}
