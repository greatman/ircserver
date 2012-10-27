package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.user.User;

public class MotdEndCodec extends Codec {

	private User conn;

	public MotdEndCodec(User conn) {
		this.conn = conn;
	}

	public String encode() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(PREFIX);
		buffer.append("376").append(" ").append(conn.getNickname()).append(" :");
		buffer.append("- END of /MOTD command");
		return buffer.toString();
	}

}
