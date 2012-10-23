package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.net.Connection;

public class MotdContentCodec implements Codec {

	private final Connection conn;
	public MotdContentCodec(Connection conn) {
		this.conn = conn;
	}
	public byte[] encode() {
		StringBuffer string = new StringBuffer();
		string.append(PREFIX).append(" ");
		string.append("372").append(conn.getNickname()).append(":");
		string.append("Content of MOTD");
		return string.toString().getBytes();
	}

}
