package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.App;
import com.greatmancode.javaserver.Channel;
import com.greatmancode.javaserver.ChannelUserModes;
import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.net.User;
import com.greatmancode.javaserver.net.codecs.ModeChannelCodec;

public class ModeCommand implements Command {

	public void run(User conn, String[] args) {
		if (args.length == 1) {
			// TODO: Support user modes
			if (args[0].contains("#")) {
				if (App.channelList.containsKey(args[0])) {
					conn.send(new ModeChannelCodec(args[0], App.channelList.get(args[0]).getModes()));
				}
			}
		} else if (args.length >= 3) {
			if (args[0].contains("#") && App.channelList.containsKey(args[0])) {
				Channel chan = App.channelList.get(args[0]);
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
			System.out.println(mode);
			User user = App.getUser(args[i + 2]);
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
