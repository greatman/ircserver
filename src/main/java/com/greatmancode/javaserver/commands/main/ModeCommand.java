package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.Server;
import com.greatmancode.javaserver.channel.Channel;
import com.greatmancode.javaserver.channel.ChannelUserModes;
import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.net.codecs.ModeChannelCodec;
import com.greatmancode.javaserver.user.User;

public class ModeCommand implements Command {

	public void run(User conn, String[] args) {
		if (args.length == 1) {
			// TODO: Support user modes
			if (args[0].contains("#")) {
				if (Server.getChannelHandler().getChannel(args[0]) != null) {
					conn.send(new ModeChannelCodec(args[0], Server.getChannelHandler().getChannel(args[0]).getModes()));
				}
			}
		} else if (args.length >= 3) {
			if (args[0].contains("#") && Server.getChannelHandler().getChannel(args[0]) != null) {
				Channel chan = Server.getChannelHandler().getChannel(args[0]);
				if (chan.getUserList().containsKey(conn) && chan.getUserList().get(conn).getUserModes().contains(ChannelUserModes.OP)) {
					if (args[1].contains("+")) {
						addRemoveModeChannel(args, chan, conn, true);
					} else if (args[1].contains("-")) {
						addRemoveModeChannel(args, chan, conn, false);
					}
				}
			}
		}

	}

	private void addRemoveModeChannel(String[] args, Channel chan, User conn, boolean add) {
		String[] temp;
		if (add) {
			temp = args[1].split("\\+");
		} else {
			temp = args[1].split("\\-");
		}
		char[] modes = temp[1].toCharArray();
		for (int i = 0; i < modes.length; i++) {
			ChannelUserModes mode = ChannelUserModes.get(String.valueOf(modes[i]));
			User user = Server.getUserHandler().getUser(args[i + 2]);
			if (mode != null && user != null && chan.getUserList().containsKey(user)) {
				if (add) {
					chan.addUserMode(conn, user, mode);
				} else {
					chan.removeUserMode(conn, user, mode);
				}
			}
		}
	}

}
