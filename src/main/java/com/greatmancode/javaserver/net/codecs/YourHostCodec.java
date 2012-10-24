package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.App;
import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.net.Connection;

public class YourHostCodec extends Codec {

	private final Connection conn;

	public YourHostCodec(Connection conn) {
		this.conn = conn;
	}

	@Override
	public String encode() {
		StringBuilder string = new StringBuilder();
		string.append(PREFIX);
		string.append("002").append(" ");
		string.append(conn.getNickname()).append(" :");
		string.append("Your host is ").append(App.getServerName())
				.append(", running version ").append(App.version);
		return string.toString();
	}
}
