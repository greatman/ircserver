package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.net.Connection;

public class MotdContentCodec extends Codec {

	private final Connection conn;

	public MotdContentCodec(Connection conn) {
		this.conn = conn;
	}

	public String encode() {
		StringBuffer string = new StringBuffer();
		string.append(PREFIX);
		string.append("372").append(" ").append(conn.getNickname()).append(":");
		string.append("Content of MOTD");
		return string.toString();
	}

}
