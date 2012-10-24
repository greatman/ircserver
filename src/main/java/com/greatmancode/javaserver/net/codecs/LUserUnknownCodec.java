package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.net.Connection;

public class LUserUnknownCodec extends Codec {

	private final Connection conn;

	public LUserUnknownCodec(Connection conn) {
		this.conn = conn;
	}

	@Override
	public String encode() {
		// TODO: Do a unknown list
		StringBuffer string = new StringBuffer();
		string.append(PREFIX);
		string.append("253").append(" ").append(conn.getNickname()).append(" ");
		string.append("0").append(" :").append("unknown connection(s)");
		return string.toString();
	}

}
