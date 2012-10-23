package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.net.Connection;

public class MotdStartCodec extends Codec {

	private final Connection conn;

	public MotdStartCodec(Connection conn) {
		this.conn = conn;
	}

	public String encode() {
		StringBuffer string = new StringBuffer();
		string.append(PREFIX);
		string.append("375").append(" ");
		string.append(conn.getNickname()).append(" :");
		string.append("Message of the day");

		return string.toString();
	}

}
