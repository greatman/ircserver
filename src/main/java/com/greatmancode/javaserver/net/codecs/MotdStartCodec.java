package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.Server;
import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.user.User;

public class MotdStartCodec extends Codec {

	private final User conn;

	public MotdStartCodec(User conn) {
		this.conn = conn;
	}

	public String encode() {
		StringBuffer string = new StringBuffer();
		string.append(PREFIX);
		string.append("375").append(" ");
		string.append(conn.getNickname()).append(" :");
		string.append("--- ").append(Server.getServer().getServerName()).append(" Message of the day ---");

		return string.toString();
	}

}
