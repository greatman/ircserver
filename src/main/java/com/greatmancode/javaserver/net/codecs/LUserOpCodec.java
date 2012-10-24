package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.net.User;

public class LUserOpCodec extends Codec {

	private final User conn;

	public LUserOpCodec(User conn) {
		this.conn = conn;
	}

	@Override
	public String encode() {
		// TODO: Real OP
		StringBuffer string = new StringBuffer();
		string.append(PREFIX);
		string.append("252").append(" ").append(conn.getNickname()).append(" ");
		string.append("0").append(" :").append("IRC Operators online");
		return string.toString();
	}

}
