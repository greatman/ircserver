package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.net.Connection;
import com.greatmancode.javaserver.net.codecs.LUserClientCodec;

public class LUsersCommand implements Command {

	public void run(Connection conn, String[] args) {
		conn.send(new LUserClientCodec(conn));

	}

}
