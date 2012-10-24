package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.Channel;
import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.net.Connection;

public class NamesCodec extends Codec {

	private final Connection conn;
	private final Channel chan;

	public NamesCodec(Connection conn, Channel chan) {
		this.conn = conn;
		this.chan = chan;
	}

	public String encode() {
		StringBuffer string = new StringBuffer();
		string.append(PREFIX);
		string.append("353").append(" ");
		string.append(conn.getNickname()).append(" = ").append(chan.getName()).append(" :");
		for (Connection user : chan.getUserList()) {
			if (chan.getOpList().contains(user)) {
				string.append("@");
			}
			string.append(user.getNickname()).append(" ");
		}
		return string.toString();
	}

}
