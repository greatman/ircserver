package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.net.Connection;
import com.greatmancode.javaserver.net.codecs.PingCodec;

public class PingCommand implements Command {

	public void run(Connection conn, String[] args) {
		String ping = args[0];
		conn.send(new PingCodec(conn, ping));

	}

}
