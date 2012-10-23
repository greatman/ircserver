package com.greatmancode.javaserver.net;

import com.greatmancode.javaserver.App;

public interface Codec {

	public static final String PREFIX = ":" + App.getServerName() + " ";
	public byte[] encode();
}
