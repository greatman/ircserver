package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.net.User;

public class NoticeCodec extends Codec {

	private final User sender, receiver;
	private final String text;

	public NoticeCodec(User sender, User receiver, String text) {
		this.sender = sender;
		this.receiver = receiver;
		this.text = text;
	}

	@Override
	public String encode() {
		StringBuffer string = new StringBuffer();
		string.append(":").append(sender.getReprensentation()).append(" ");
		string.append("NOTICE").append(" ").append(receiver.getNickname()).append(" :").append(text);
		return string.toString();
	}

}
