package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.App;
import com.greatmancode.javaserver.Channel;
import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.net.Connection;

public class WhoCodec extends Codec {

	private final Connection conn, connMember;
	private final Channel chan;

	public WhoCodec(Connection conn, Connection connMember, Channel chan) {
		this.conn = conn;
		this.chan = chan;
		this.connMember = connMember;
	}

	public String encode() {
		StringBuffer string = new StringBuffer();
		string.append("352").append(" ").append(conn.getNickname()).append(" ");
		string.append(chan.getName()).append(" ");
		string.append(connMember.getRealName()).append(" ").append(connMember.getHost()).append(" ");
		string.append(App.getServerName()).append(" ").append(connMember.getNickname()).append(" H :0 ");
		// TODO: Add version
		String content = string.toString().replace("\n", "").replace("\r", "");
        content = content + "\r\n";
        return string.toString();
	}

}
