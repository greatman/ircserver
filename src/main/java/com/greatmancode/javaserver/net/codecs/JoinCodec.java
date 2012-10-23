package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.net.Connection;

public class JoinCodec implements Codec {

	private final Connection conn;
	private final String channel;
	public JoinCodec(Connection conn, String channel) {
		this.conn = conn;
		this.channel = channel;
	}
	
	public byte[] encode() {
		StringBuilder string = new StringBuilder();
		string.append(conn.getNickname()).append("!").append(conn.getRealName()).append("@").append(conn.getHost());
		string.append(" JOIN ").append(channel);
		return string.toString().getBytes();
	}

}
