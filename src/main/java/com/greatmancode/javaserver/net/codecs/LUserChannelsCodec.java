package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.Server;
import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.user.User;

public class LUserChannelsCodec extends Codec {

	private final User conn;
	public LUserChannelsCodec(User conn) {
		this.conn = conn;
	}

	@Override
	public String encode() {
		StringBuffer string = new StringBuffer();
		string.append(PREFIX);
		string.append("254").append(" ");
		string.append(conn.getNickname()).append(" ");
		string.append(Server.getChannelHandler().getSize()).append(" :Channels formed");
		return string.toString();
	}

}
