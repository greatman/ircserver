package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.Server;
import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.user.User;

public class WelcomeCodec extends Codec {

	private final User conn;

	public WelcomeCodec(User conn) {
		this.conn = conn;
	}

	public String encode() {
		StringBuilder string = new StringBuilder();
		string.append(PREFIX);
		string.append("001 ").append(conn.getNickname()).append(" :");
		string.append("Welcome to the ").append(Server.getServerName())
				.append(" Internet Relay Chat Network ")
				.append(conn.getNickname())
				.append(", a Java-powered IRC network!");
		return string.toString();
	}
}
