package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.user.User;

public class WhoisEndCodec extends Codec {


	private User user;

	public WhoisEndCodec(User user) {
		this.user = user;
	}

	@Override
	public String encode() {
		StringBuilder string = new StringBuilder();
		string.append(PREFIX);
		string.append("318 ").append(user.getNickname()).append(" :");
		string.append("End of /WHOIS list");
		return string.toString();
	}
}
