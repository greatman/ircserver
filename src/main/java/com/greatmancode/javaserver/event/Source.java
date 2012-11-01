package com.greatmancode.javaserver.event;

import com.greatmancode.javaserver.net.Codec;

/**
 * Used to know from who this happens. It can be Server or User
 * @author greatman
 *
 */
public interface Source {

	String getReprensentation();
	void send(Codec content);
	String getNickname();

}
