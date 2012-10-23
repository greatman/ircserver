package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.net.Connection;

public class MotdStartCodec implements Codec {

	private final Connection conn;

	public MotdStartCodec(Connection conn) {
		this.conn = conn;
	}

	public byte[] encode() {
		StringBuffer string = new StringBuffer();
		string.append(PREFIX);
		string.append(conn.getNickname()).append(" :");
		string.append("Message of the day");

		return string.toString().getBytes();
	}

}
