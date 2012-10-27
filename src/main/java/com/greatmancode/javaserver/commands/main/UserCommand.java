package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.user.User;

public class UserCommand implements Command {

	public void run(User conn, String[] args) {
		conn.setRealName(args[0]);
	}

}
