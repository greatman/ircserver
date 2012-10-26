package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.App;
import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.net.User;

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
		string.append("--- ").append(App.getServerName()).append(" Message of the day ---");

		return string.toString();
	}

}
