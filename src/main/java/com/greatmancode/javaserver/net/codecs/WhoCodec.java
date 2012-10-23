package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.net.Connection;

public class WhoCodec implements Codec {

	private Connection conn;
	private Channel chan;
	public void WhoCodec(Connection conn, Channel chan) {
		this.conn = conn;
		this.chan = chan;
	}
	public byte[] encode() {
		StringBuffer string = new StringBuffer();
		string.append("352")
		con.sendGlobal("352 " + con.nick + " " + person
                + " " + channelMember.username + " " + channelMember.hostname
                + " " + globalServerName + " " + channelMember.nick
                + " H :0 " + channelMember.description);
    
	}

}
