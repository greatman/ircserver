package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.Server;
import com.greatmancode.javaserver.channel.Channel;
import com.greatmancode.javaserver.channel.ChannelUserMode;
import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.user.User;

public class KickCommand implements Command {

	public void run(User conn, String[] args) {
		if (args.length >= 2 && Server.getServer().getChannelHandler().getChannel(args[0]) != null) {
			Channel chan = Server.getServer().getChannelHandler().getChannel(args[0]);
			if (chan.getUserList().containsKey(conn) && chan.getUserList().get(conn).getUserModes().contains(ChannelUserMode.OP)) {
				User kicked = Server.getServer().getUserHandler().getUser(args[1]);
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
