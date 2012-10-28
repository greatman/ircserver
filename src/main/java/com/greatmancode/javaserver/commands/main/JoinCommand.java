package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.Server;
import com.greatmancode.javaserver.channel.Channel;
import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.user.User;

public class JoinCommand implements Command{

	public void run(User user, String[] args) {
		Channel chan = Server.getServer().getChannelHandler().getChannel(args[0]);
		if (chan != null) {
			chan.addUser(user);
		} else {
			chan = Server.getServer().getChannelHandler().addChannel(args[0]);
			chan.addUser(user);
			
		}
		
	}

}
