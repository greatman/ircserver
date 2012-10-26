package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.App;
import com.greatmancode.javaserver.Channel;
import com.greatmancode.javaserver.ChannelUserModes;
import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.net.User;

public class KickCommand implements Command {

	public void run(User conn, String[] args) {
		if (args.length >= 2) {
			if (App.channelList.containsKey(args[0])) {
				Channel chan = App.channelList.get(args[0]);
				if (chan.getUserList().containsKey(conn) && chan.getUserList().get(conn).getUserModes().contains(ChannelUserModes.OP)) {
					User kicked = App.getUser(args[1]);
					if (kicked != null) {
						if (chan.getUserList().containsKey(kicked)) {
							// TODO : Need fixes? Probably
							if (args.length >= 3) {
								String reason = "";
								for (int i = 2; i < args.length; i++) {
									reason += args[i] + " ";
								}
								chan.kickUser(conn, kicked, reason);
							} else {
								chan.kickUser(conn, kicked);
							}
						}
					}

				}

			}
		}
	}
}
