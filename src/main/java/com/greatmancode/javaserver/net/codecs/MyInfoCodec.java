package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.Server;
import com.greatmancode.javaserver.channel.ChannelMode;
import com.greatmancode.javaserver.channel.ChannelUserMode;
import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.user.User;
import com.greatmancode.javaserver.user.UserModes;

public class MyInfoCodec extends Codec {

	private final User conn;

	public MyInfoCodec(User conn) {
		this.conn = conn;
	}

	public String encode() {
		StringBuilder string = new StringBuilder();
		string.append(PREFIX);
		string.append("004 ").append(conn.getNickname()).append(" ");
		string.append(Server.getServer().getServerName()).append(" ");
		string.append(Server.VERSION).append(" ");
		for (UserModes type : UserModes.values()) {
			string.append(type);
		}
		string.append(" ");
		for (ChannelMode type: ChannelMode.values()) {
			string.append(type);
		}
		for(ChannelUserMode type: ChannelUserMode.values()) {
			string.append(type);
		}
		
		return string.toString();
	}
}
