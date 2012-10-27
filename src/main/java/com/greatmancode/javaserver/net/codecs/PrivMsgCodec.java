package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.channel.Channel;
import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.user.User;

public class PrivMsgCodec extends Codec {

	private final String msg;
	private final User sender, receiver;
	private final Channel chan;
	public PrivMsgCodec(User sender, User receiver, String msg) {
		this.sender = sender;
		this.receiver = receiver;
		this.msg = msg;
		this.chan = null;
	}
	public PrivMsgCodec(User sender, Channel chan, String msg) {
		this.msg = msg;
		this.sender = sender;
		this.chan = chan;
		this.receiver = null;
	}
	@Override
	public String encode() {
		StringBuffer string = new StringBuffer();
		string.append(":").append(sender.getReprensentation()).append(" ");
		string.append("PRIVMSG ");
		if (chan != null) {
			string.append(chan.getName());
		} else {
			string.append(receiver.getNickname());
		}
		string.append(" :").append(msg);
		return string.toString();
		
	}

}
