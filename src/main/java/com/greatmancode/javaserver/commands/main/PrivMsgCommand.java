package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.Server;
import com.greatmancode.javaserver.channel.Channel;
import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.event.events.UserChannelMessageEvent;
import com.greatmancode.javaserver.net.codecs.NoSuchNicknameChannelCodec;
import com.greatmancode.javaserver.net.codecs.PrivMsgCodec;
import com.greatmancode.javaserver.user.User;

public class PrivMsgCommand implements Command {

	public void run(User conn, String[] args) {
		if (args[0].contains("#")) {
			Channel chan = Server.getServer().getChannelHandler().getChannel(args[0]);
			if (chan != null) {
				UserChannelMessageEvent event = (UserChannelMessageEvent) Server.getServer().getEventManager().callEvent(new UserChannelMessageEvent(conn, chan, args[1]));
				if (!event.isCancelled()) {
					event.getChannel().sendMessage(conn, event.getMessage());
				}
			}
			
		} else {
			User user = Server.getServer().getUserHandler().getUser(args[0]);
			if (user != null) {
				user.send(new PrivMsgCodec(conn, user, args[1]));
			} else {
				conn.send(new NoSuchNicknameChannelCodec(conn, args[1]));
			}
		}

	}

}
