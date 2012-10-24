package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.net.Connection;

public class QuitCodec extends Codec {

	private final Connection conn;

	public QuitCodec(Connection conn) {
		this.conn = conn;
	}

	@Override
	public String encode() {
		//TODO: SUpport custom quit msgs.
		StringBuffer string = new StringBuffer();
		string.append(":" + conn.getNickname()).append("!").append(conn.getRealName()).append("@").append(conn.getHost()).append(" ");
		string.append("QUIT ").append(":Client Quit");
		return string.toString();
	}

}
