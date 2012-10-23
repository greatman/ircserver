package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.net.Connection;

public class MotdEndCodec extends Codec {

	private Connection conn;

	public MotdEndCodec(Connection conn) {
		this.conn = conn;
	}

	public String encode() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(PREFIX);
		buffer.append("376").append(" ").append(conn.getNickname()).append(":");
		buffer.append("END of /MOTD command");
		return buffer.toString();
	}

}
