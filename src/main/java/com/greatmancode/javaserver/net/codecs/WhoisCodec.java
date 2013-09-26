package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.Server;
import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.user.User;

public class WhoisCodec extends Codec {

	private User user;
	public WhoisCodec(User user) {
		this.user = user;
	}
	@Override
	public String encode() {
		StringBuilder string = new StringBuilder();
		string.append(PREFIX);
		string.append("311 ").append(user.getNickname()).append(" :");
		string.append(user.getNickname()).append(" ~").append(user.getNickname()).append(" ");
		string.append(user.getHost()).append(" ").append("*").append(" :");
		string.append(user.getRealName());
		return string.toString();

	}
}
