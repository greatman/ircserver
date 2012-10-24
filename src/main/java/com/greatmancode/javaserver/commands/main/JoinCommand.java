package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.App;
import com.greatmancode.javaserver.Channel;
import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.net.User;

public class JoinCommand implements Command{

	public void run(User conn, String[] args) {
		if (App.channelList.containsKey(args[0])) {
			App.channelList.get(args[0]).addUser(conn);
		} else {
			Channel chan = new Channel(args[0]);
			chan.addOp(conn);
			chan.addUser(conn);
			App.channelList.put(args[0], chan);
		}
		
	}

}
