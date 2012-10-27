package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.Server;
import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.user.User;

public class PartCommand implements Command {

	public void run(User conn, String[] args) {
		if (args.length >= 1 && Server.CHANNEL_LIST.containsKey(args[0]) && Server.CHANNEL_LIST.get(args[0]).getUserList().containsKey(conn)) {
			Server.CHANNEL_LIST.get(args[0]).removeUser(conn, false);
		}
	}

}
