package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.App;
import com.greatmancode.javaserver.UserModes;
import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.net.User;

public class MyInfoCodec extends Codec {

	private final User conn;

	public MyInfoCodec(User conn) {
		this.conn = conn;
	}

	public String encode() {
		StringBuilder string = new StringBuilder();
		string.append(PREFIX);
		string.append("004 ").append(conn.getNickname()).append(" ");
		string.append(App.getServerName()).append(" ");
		string.append("JavaIRCServer0.1").append(" ");
		for (UserModes type : UserModes.values()) {
			string.append(type);
		}
		string.append(" ");
		string.append("opsitnmlbvk");
		
		return string.toString();
	}
}
