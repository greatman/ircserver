package com.greatmancode.javaserver.net;

import com.greatmancode.javaserver.App;

public abstract class Codec {

	public static final String PREFIX = ":" + App.getServerName() + " ";
	
	public String toSend() {
		String content = encode().replace("\n", "").replace("\r", "");
        content = content + "\r\n";
        System.out.println("Sending: " + content);
		return content;
	}
	
	public abstract String encode();
}
