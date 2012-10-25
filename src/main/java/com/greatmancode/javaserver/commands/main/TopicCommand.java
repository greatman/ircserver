package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.App;
import com.greatmancode.javaserver.Channel;
import com.greatmancode.javaserver.ChannelUserModes;
import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.net.User;

public class TopicCommand implements Command {

	public void run(User conn, String[] args) {
		if (args.length >= 1) {
			if (App.channelList.containsKey(args[0])) {
				Channel chan = App.channelList.get(args[0]);
				if (chan.getUserList().containsKey(conn) && chan.getUserList().get(conn).getUserModes().contains(ChannelUserModes.OP)) {
					//TODO: Remove?
					String topic = "";
					for (int i = 1; i < args.length; i++) {
						topic += args[i];
						if (i < args.length - 1) {
							topic += " ";
						}
					}
					chan.setTopic(conn, topic);
				}
			}
		}

	}

}
