package com.greatmancode.javaserver.net;

import com.greatmancode.javaserver.App;

/**
 * Represents a codec. Also manage the how to make the codec send.
 * @author greatman
 *
 */
public abstract class Codec {

	public static final String PREFIX = ":" + App.getServerName() + " ";
	
	/**
	 * The codec formatted to be sent.
	 * @return The codec formatted to be sent.
	 */
	public String toSend() {
		String content = encode().replace("\n", "").replace("\r", "");
        content = content + "\r\n";
		return content;
	}
	
	/**
	 * Encode the codec.
	 * @return The codec encoded.
	 */
	public abstract String encode();
}
