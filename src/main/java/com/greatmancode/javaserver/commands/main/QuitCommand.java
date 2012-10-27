package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.net.codecs.QuitCodec;
import com.greatmancode.javaserver.user.User;

public class QuitCommand implements Command {

	public void run(User conn, String[] args) {
		conn.send(new QuitCodec(conn));
		conn.disconnect();
		
	}

}
