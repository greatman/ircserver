package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.App;
import com.greatmancode.javaserver.Channel;
import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.net.User;

public class PrivMsgCommand implements Command {

	public void run(User conn, String[] args) {
		Channel chan = App.channelList.get(args[0]);
		String[] message = new String[args.length - 1];
		System.arraycopy(args, 1, message, 0, args.length - 1);
		if (chan != null) {
			chan.sendMessage(conn, message);
		}
		
	}

}
