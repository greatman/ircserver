package com.greatmancode.javaserver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.greatmancode.javaserver.net.Connection;

public class Channel {

	private List<Connection> userList = new ArrayList<Connection>();
	private String name;
	private String topic;
	
	public Channel(String name) {
		this.name = name;
	}
	public void addUser(Connection conn) {
		userList.add(conn);
		conn.send(conn.getNickname() + "!" + conn.getRealName() + "@" + conn.getHost() + " JOIN " + name);
		conn.send(":test MODE " + name + " +nt");
		conn.send(":test 331 " + conn.getNickname() + " " + name + " :test");
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
