package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.net.Connection;
import com.greatmancode.javaserver.net.codecs.LUserChannelsCodec;
import com.greatmancode.javaserver.net.codecs.LUserClientCodec;
import com.greatmancode.javaserver.net.codecs.LUserOpCodec;
import com.greatmancode.javaserver.net.codecs.LUserUnknownCodec;

public class LUsersCommand implements Command {

	public void run(Connection conn, String[] args) {
		conn.send(new LUserClientCodec(conn));
		conn.send(new LUserOpCodec(conn));
		conn.send(new LUserUnknownCodec(conn));
		conn.send(new LUserChannelsCodec(conn));
	}

}
