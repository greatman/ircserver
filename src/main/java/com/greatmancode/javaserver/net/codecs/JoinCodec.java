package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.net.User;

public class JoinCodec extends Codec {

	private final User conn;
	private final String channel;
	public JoinCodec(User conn, String channel) {
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
