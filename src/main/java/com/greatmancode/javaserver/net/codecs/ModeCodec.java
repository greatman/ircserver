package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.net.Connection;

public class ModeCodec implements Codec {

	private final String userChannel, mode;
	private final Connection conn;
	public ModeCodec(Connection conn, String userChannel, String mode) {
		this.userChannel = userChannel;
		this.mode = mode;
		this.conn = conn;
	}
	public byte[] encode() {
		StringBuilder string = new StringBuilder();
		string.append(PREFIX);
		string.append(conn.getNickname()).append(" ");
		string.append(userChannel).append(" ");
		string.append(mode);
		return string.toString().getBytes();
	}
}
