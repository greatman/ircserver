package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.App;
import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.net.User;

public class LUserClientCodec extends Codec {

	private final User conn;

	public LUserClientCodec(User conn) {
		this.conn = conn;
	}

	@Override
	public String encode() {
		StringBuilder string = new StringBuilder();
		string.append(PREFIX);
		string.append("251 ").append(conn.getNickname()).append(" :");
		string.append("There are ").append(App.getUserHandler().size()).append(" users and 0 invisible on 1 servers");
		return string.toString();
	}

}
