package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.App;
import com.greatmancode.javaserver.Channel;
import com.greatmancode.javaserver.ChannelUserModes;
import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.net.User;

public class KickCommand implements Command {

	public void run(User conn, String[] args) {
		if (args.length >= 2 && App.CHANNEL_LIST.containsKey(args[0])) {
			Channel chan = App.CHANNEL_LIST.get(args[0]);
			if (chan.getUserList().containsKey(conn) && chan.getUserList().get(conn).getUserModes().contains(ChannelUserModes.OP)) {
				User kicked = App.getUser(args[1]);
				if (kicked != null && chan.getUserList().containsKey(kicked)) {
					if (args.length >= 3) {
						chan.kickUser(conn, kicked, args[2]);
					} else {
						chan.kickUser(conn, kicked);
					}
				}
			}
		}
	}
}
