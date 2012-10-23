package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.net.Connection;

public class NoTopicCodec implements Codec {

	private final Connection conn;
	private final String channelName;
	public NoTopicCodec(Connection conn, String channelName) {
		this.conn = conn;
		this.channelName = channelName;
	}

	public byte[] encode() {
		StringBuilder string = new StringBuilder();
		string.append(PREFIX);
		string.append("331").append(" ");
		string.append(conn.getNickname()).append(" ");
		string.append(channelName).append(" :No Topic is set");
		return string.toString().getBytes();
	}
	
	
}
