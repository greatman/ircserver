package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.Channel;
import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.net.Connection;

public class NamesEndCodec extends Codec {

	private final Connection conn;
	private final Channel chan;

	public NamesEndCodec(Connection conn, Channel chan) {
		this.conn = conn;
		this.chan = chan;
	}

	public String encode() {
		StringBuffer string = new StringBuffer();
		string.append(PREFIX);
		string.append("366").append(" ");
		string.append(conn.getNickname()).append(" ");
		string.append(chan.getName()).append(" :End of /NAMES list");
		return string.toString();
	}

}
