package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.net.Connection;

public class JoinCodec extends Codec {

	private final Connection conn;
	private final String channel;
	public JoinCodec(Connection conn, String channel) {
		this.conn = conn;
		this.channel = channel;
	}
	
	@Override
	public String encode() {
		StringBuilder string = new StringBuilder();
		string.append(":").append(conn.getReprensentation());
		string.append(" JOIN ").append(channel);
		return string.toString();
	}

}
