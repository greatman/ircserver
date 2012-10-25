package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.App;
import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.net.User;

public class PartCommand implements Command {

	public void run(User conn, String[] args) {
		if (args.length >= 1) {
			if (App.channelList.containsKey(args[0])) {
				if (App.channelList.get(args[0]).getUserList().containsKey(conn)) {
					App.channelList.get(args[0]).removeUser(conn, false);
				}
			}
		}
		
		
	}

}
