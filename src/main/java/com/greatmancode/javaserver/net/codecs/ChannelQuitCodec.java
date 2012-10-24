package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.Channel;
import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.net.User;

public class ChannelQuitCodec extends Codec {

	private final User conn;
	private final Channel chan;
	public ChannelQuitCodec(User conn, Channel chan) {
		this.conn = conn;
		this.chan = chan;
	}
	@Override
	public String encode() {
		StringBuffer string = new StringBuffer();
		string.append(":").append(conn.getReprensentation()).append(" PART ").append(chan.getName());
		// TODO Auto-generated method stub
		return string.toString();
	}

}
