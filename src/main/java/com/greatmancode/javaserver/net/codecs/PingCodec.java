package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.App;
import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.net.Connection;

public class PingCodec extends Codec {

	private final String ping;
	private final Connection conn;
	public PingCodec(Connection conn, String ping) {
		this.conn = conn;
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
