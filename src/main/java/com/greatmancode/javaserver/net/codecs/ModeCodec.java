package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.net.Codec;

public class ModeCodec extends Codec {

	private final String userChannel, mode;

	public ModeCodec(String userChannel, String mode) {
		this.userChannel = userChannel;
		this.mode = mode;
	}

	public String encode() {
		StringBuilder string = new StringBuilder();
		string.append(PREFIX);
		string.append("MODE").append(" ");
		string.append(userChannel).append(" ");
		string.append(mode);
		return string.toString();
	}
}
