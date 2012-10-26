package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.App;
import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.net.User;

public class PartCommand implements Command {

	public void run(User conn, String[] args) {
		if (args.length >= 1 && App.CHANNEL_LIST.containsKey(args[0]) && App.CHANNEL_LIST.get(args[0]).getUserList().containsKey(conn)) {
			App.CHANNEL_LIST.get(args[0]).removeUser(conn, false);
		}
	}

}
