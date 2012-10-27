package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.Server;
import com.greatmancode.javaserver.channel.Channel;
import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.user.User;

public class WhoCodec extends Codec {

	private final User conn, connMember;
	private final Channel chan;

	public WhoCodec(User conn, User connMember, Channel chan) {
		this.conn = conn;
		this.chan = chan;
		this.connMember = connMember;
	}

	public String encode() {
		StringBuffer string = new StringBuffer();
		string.append(PREFIX).append("352").append(" ").append(conn.getNickname()).append(" ");
		string.append(chan.getName()).append(" ");
		string.append(connMember.getNickname()).append(" ").append(connMember.getHost()).append(" ");
		string.append(Server.getServerName()).append(" ").append(connMember.getNickname()).append(" H :0 ").append(connMember.getRealName());
		// TODO: Add version
        return string.toString();
	}

}
