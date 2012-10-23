package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.net.Connection;
import com.greatmancode.javaserver.net.codecs.ModeCodec;

public class ModeCommand implements Command {

	public void run(Connection conn, String[] args) {
		
		conn.send(new ModeCodec(conn, args[0], args[1]));

	}

}
