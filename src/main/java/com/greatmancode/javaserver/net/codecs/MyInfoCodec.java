package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.net.Connection;

public class MyInfoCodec extends Codec {

	private final Connection conn;

	public MyInfoCodec(Connection conn) {
		this.conn = conn;
	}

	public String encode() {
		StringBuilder string = new StringBuilder();
		string.append(PREFIX);
		string.append("004 ").append(conn.getNickname()).append(":");
		string.append("JavaIrc").append(" ");
		string.append("0.1").append(" ");
		string.append("+rs").append(" ");
		string.append("+mn");
		return string.toString();
	}
}
