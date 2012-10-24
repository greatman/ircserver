package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.Channel;
import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.net.User;

public class KickCodec extends Codec {

	private final User operator, kicked;
	private final Channel chan;
	private final String reason;

	public KickCodec(User operator, User kicked, Channel chan,
			String reason) {
		this.operator = operator;
		this.kicked = kicked;
		this.chan = chan;
		this.reason = reason;
	}

	@Override
	public String encode() {
		StringBuffer string = new StringBuffer();
		string.append(":").append(operator.getReprensentation()).append(" ");
		string.append("KICK").append(" ").append(chan.getName()).append(" ");
		string.append(kicked.getNickname()).append(" ").append(reason);
		return string.toString();
	}

}
