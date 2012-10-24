package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.App;
import com.greatmancode.javaserver.Channel;
import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.net.Connection;

public class TopicCommand implements Command {

	public void run(Connection conn, String[] args) {
		if (args.length >= 1) {
			if (App.channelList.containsKey(args[0])) {
				Channel chan = App.channelList.get(args[0]);
				if (chan.getOpList().contains(conn)) {
					String topic = "";
					for (int i = 1; i < args.length; i++) {
						topic += args[i];
						if (i < args.length -1) {
							topic += " ";
						}
					}
					chan.setTopic(conn, topic);
				}
			}
		}

	}

}
