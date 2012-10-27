package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.user.User;

public class NoSuchNicknameChannelCodec extends Codec {

	private final User user;
	private final String nickname;

	public NoSuchNicknameChannelCodec(User user, String nickname) {
		this.user = user;
		this.nickname = nickname;
	}

	@Override
	public String encode() {
		StringBuilder string = new StringBuilder();
		string.append(PREFIX);
		string.append("401").append(" ");
		string.append(user.getNickname()).append(" ");
		string.append(nickname).append(" :No suck nick/channel");
		return string.toString();
	}

}
