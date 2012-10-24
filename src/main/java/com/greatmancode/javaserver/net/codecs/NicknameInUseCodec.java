package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.net.Codec;

public class NicknameInUseCodec extends Codec {

	private final String nickname;
	public NicknameInUseCodec(String nickname) {
		this.nickname = nickname;
	}
	@Override
	public String encode() {
		StringBuffer string = new StringBuffer();
		string.append(PREFIX);
		string.append("433").append(" * ");
		string.append(nickname).append(" ");
		string.append(":Nickname is already in use.");
		return string.toString();
	}

}
