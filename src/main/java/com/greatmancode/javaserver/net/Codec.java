package com.greatmancode.javaserver.net;

import com.greatmancode.javaserver.App;

public abstract class Codec {

	public static final String PREFIX = ":" + App.getServerName() + " ";
	
	public byte[] toSend() {
		String content = encode().replace("\n", "").replace("\r", "");
        content = content + "\r\n";
        System.out.println("Gonna send: " + content);
		return content.getBytes();
	}
	
	public abstract String encode();
}
