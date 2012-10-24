package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.net.User;

public class NoTopicCodec extends Codec {

	private final User conn;
	private final String channelName;
	public NoTopicCodec(User conn, String channelName) {
		this.conn = conn;
		this.channelName = channelName;
	}

	public String encode() {
		StringBuilder string = new StringBuilder();
		string.append(PREFIX);
		string.append("331").append(" ");
		string.append(conn.getNickname()).append(" ");
		string.append(channelName).append(" :No Topic is set");
		return string.toString();
	}
	
	
}
