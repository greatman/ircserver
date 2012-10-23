package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.Channel;
import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.net.Connection;

public class NamesCodec extends Codec {

	private final Connection conn, user;
	private final Channel chan;

	public NamesCodec(Connection conn, Channel chan, Connection user) {
		this.conn = conn;
		this.chan = chan;
		this.user = user;
	}

	public String encode() {
		StringBuffer string = new StringBuffer();
		string.append(PREFIX);
		string.append("353").append(" ");
		string.append(conn.getNickname()).append(" = ").append(chan.getName()).append(" :");
		string.append(user.getNickname());
		return string.toString();
	}

}
