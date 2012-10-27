package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.channel.Channel;
import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.user.User;

public class ChannelPartCodec extends Codec {

	private final User conn;
	private final Channel chan;
	public ChannelPartCodec(User conn, Channel chan) {
		this.conn = conn;
		this.chan = chan;
	}
	@Override
	public String encode() {
		StringBuffer string = new StringBuffer();
		string.append(":").append(conn.getReprensentation()).append(" PART ").append(chan.getName());
		return string.toString();
	}

}
