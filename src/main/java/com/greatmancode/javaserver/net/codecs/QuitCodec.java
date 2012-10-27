package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.user.User;

public class QuitCodec extends Codec {

	private final User conn;

	public QuitCodec(User conn) {
		this.conn = conn;
	}

	@Override
	public String encode() {
		//TODO: SUpport custom quit msgs.
		StringBuffer string = new StringBuffer();
		string.append(":").append(conn.getReprensentation()).append(" ");
		string.append("QUIT ").append(":Client Quit");
		return string.toString();
	}

}
