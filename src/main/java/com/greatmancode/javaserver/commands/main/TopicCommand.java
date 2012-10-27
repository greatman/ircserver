package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.Server;
import com.greatmancode.javaserver.channel.Channel;
import com.greatmancode.javaserver.channel.ChannelUserModes;
import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.user.User;

public class TopicCommand implements Command {

	public void run(User conn, String[] args) {
		if (args.length >= 1 && Server.getChannelHandler().getChannel(args[0]) != null) {
			Channel chan = Server.getChannelHandler().getChannel(args[0]);
			if (chan.getUserList().containsKey(conn) && chan.getUserList().get(conn).getUserModes().contains(ChannelUserModes.OP)) {
				chan.setTopic(conn, args[1]);
			}
		}

	}

}
