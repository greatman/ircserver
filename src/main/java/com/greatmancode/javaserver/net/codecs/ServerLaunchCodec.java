package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.App;
import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.net.User;

public class ServerLaunchCodec extends Codec {

	private final User conn;

	public ServerLaunchCodec(User conn) {
		this.conn = conn;
	}

	@Override
	public String encode() {
		StringBuilder string = new StringBuilder();
		string.append(PREFIX);
		string.append("003").append(" ");
		string.append(conn.getNickname()).append(" :");
		string.append("This server was created ").append(App.launchDate);
		return string.toString();
	}

}
