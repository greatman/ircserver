package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.Server;
import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.user.User;

public class PartCommand implements Command {

	public void run(User user, String[] args) {
		if (args.length >= 1 && Server.getChannelHandler().getChannel(args[0]) != null && Server.getChannelHandler().getChannel(args[0]).getUserList().containsKey(user)) {
			Server.getChannelHandler().getChannel(args[0]).removeUser(user, false);
		}
	}

}
