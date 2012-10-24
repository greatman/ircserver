package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.net.Connection;

public class UserCommand implements Command {

	public void run(Connection conn, String[] args) {
		conn.setRealName(args[0]);
	}

}
