package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.net.Connection;
import com.greatmancode.javaserver.net.codecs.QuitCodec;

public class QuitCommand implements Command {

	public void run(Connection conn, String[] args) {
		conn.send(new QuitCodec(conn));
		conn.disconnect();
		
	}

}
