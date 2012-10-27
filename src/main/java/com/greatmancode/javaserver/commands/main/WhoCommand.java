package com.greatmancode.javaserver.commands.main;

import java.util.Iterator;

import com.greatmancode.javaserver.Server;
import com.greatmancode.javaserver.channel.Channel;
import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.net.codecs.WhoCodec;
import com.greatmancode.javaserver.user.User;

public class WhoCommand implements Command {

	public void run(User conn, String[] args) {
		//TODO: Support more than just chans
		//TODO: Easy to NPE
		if (Server.getChannelHandler().getChannel(args[0]) != null) {
			Channel chan = Server.getChannelHandler().getChannel(args[0]);
			Iterator<User> iterator = chan.getUserList().keySet().iterator();
			while (iterator.hasNext()) {
				conn.send(new WhoCodec(conn, iterator.next(), chan));
	        }
		}
	}
}
