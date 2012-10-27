package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.Server;
import com.greatmancode.javaserver.channel.Channel;
import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.user.User;

public class JoinCommand implements Command{

	public void run(User conn, String[] args) {
		if (Server.CHANNEL_LIST.containsKey(args[0])) {
			Server.CHANNEL_LIST.get(args[0]).addUser(conn);
		} else {
			Channel chan = new Channel(args[0]);
			chan.addUser(conn);
			Server.CHANNEL_LIST.put(args[0], chan);
		}
		
	}

}
