package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.Channel;
import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.net.Connection;

public class PrivMsgCodec extends Codec {

	private final String msg;
	private final Connection sender;
	private final Channel chan;
	public PrivMsgCodec(Connection sender, Channel chan, String msg) {
		this.msg = msg;
		this.sender = sender;
		this.chan = chan;
	}
	@Override
	public String encode() {
		StringBuffer string = new StringBuffer();
		string.append(":" + sender.getNickname()).append("!").append(sender.getRealName()).append("@").append(sender.getHost()).append(" ");
		string.append("PRIVMSG ").append(chan.getName()).append(" ").append(msg);
		return string.toString();
		
	}

}
