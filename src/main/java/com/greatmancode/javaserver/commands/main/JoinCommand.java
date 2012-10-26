package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.App;
import com.greatmancode.javaserver.Channel;
import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.net.User;

public class JoinCommand implements Command{

	public void run(User conn, String[] args) {
		if (App.CHANNEL_LIST.containsKey(args[0])) {
			App.CHANNEL_LIST.get(args[0]).addUser(conn);
		} else {
			Channel chan = new Channel(args[0]);
			chan.addUser(conn);
			App.CHANNEL_LIST.put(args[0], chan);
		}
		
	}

}
