package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.channel.Channel;
import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.user.User;

public class TopicCodec extends Codec {

	private final User conn;
	private final Channel chan;

	public TopicCodec(User conn, Channel chan) {
		this.conn = conn;
		this.chan = chan;
	}

	@Override
	public String encode() {
		StringBuffer string = new StringBuffer();
		string.append(":").append(conn.getReprensentation()).append(" ");
		string.append("TOPIC").append(" ").append(chan.getName()).append(" :");
		string.append(chan.getTopic());
		return string.toString();
	}

}
