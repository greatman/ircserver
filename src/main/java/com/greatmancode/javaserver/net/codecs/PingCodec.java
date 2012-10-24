package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.App;
import com.greatmancode.javaserver.net.Codec;

public class PingCodec extends Codec {

	private final String ping;
	public PingCodec(String ping) {
		this.ping = ping;
	}
	@Override
	public String encode() {
		StringBuffer string = new StringBuffer();
		string.append(PREFIX);
		string.append("PONG ").append(App.getServerName()).append(" ").append(ping);
		return string.toString();
	}

}
