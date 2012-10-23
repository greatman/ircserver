package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.net.Connection;

public class MotdContentCodec implements Codec {

	private final Connection conn;
	public MotdContentCodec(Connection conn) {
		this.conn = conn;
	}
	public byte[] encode() {
		// TODO Auto-generated method stub
		return null;
	}

}
