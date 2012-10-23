package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.App;
import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.net.Connection;

public class WelcomeCodec implements Codec {

	private final Connection conn;
	public WelcomeCodec(Connection conn) {
		this.conn = conn;
	}
	public byte[] encode() {
		StringBuilder string = new StringBuilder();
		string.append(PREFIX);
		string.append("001 ").append(conn.getNickname()).append(" :");
		string.append("Welcome to ").append(App.getServerName()).append(", a Java-powered IRC network!");
		return string.toString().getBytes();
	}	
}
