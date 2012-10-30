/*
 * This file is part of javaserver.
 *
 * Copyright (c) 2011-2012,
 * 							${project.organization.name} <${url}/>
 *
 * javaserver is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * javaserver is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with javaserver.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.greatmancode.javaserver.commands.main;

import java.util.ArrayList;
import java.util.List;

import com.greatmancode.javaserver.Server;
import com.greatmancode.javaserver.channel.Channel;
import com.greatmancode.javaserver.channel.ChannelMode;
import com.greatmancode.javaserver.channel.ChannelUserMode;
import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.net.codecs.ModeChannelCodec;
import com.greatmancode.javaserver.user.User;

public class ModeCommand implements Command {

	public void run(User conn, String[] args) {
		if (args.length == 1) {
			// TODO: Support user modes
			if (args[0].contains("#")) {
				if (Server.getServer().getChannelHandler().getChannel(args[0]) != null) {
					conn.send(new ModeChannelCodec(args[0], Server.getServer().getChannelHandler().getChannel(args[0]).getModes()));
				}
			}
		} else if (args.length == 2) {
			if (args[0].contains("#") && Server.getServer().getChannelHandler().getChannel(args[0]) != null) {
				Channel chan = Server.getServer().getChannelHandler().getChannel(args[0]);
				if (args[1].contains("+")) {
					modifyChannelMode(args, chan, conn, true);
				} else if (args[1].contains("-")) {
					modifyChannelMode(args, chan, conn, false);
				}
			}
		} else if (args.length >= 3) {
			if (args[0].contains("#") && Server.getServer().getChannelHandler().getChannel(args[0]) != null) {
				Channel chan = Server.getServer().getChannelHandler().getChannel(args[0]);
				if (chan.getUserList().containsKey(conn) && chan.getUserList().get(conn).getUserModes().contains(ChannelUserMode.OP)) {
					if (args[1].contains("+")) {
						addRemoveModeChannel(args, chan, conn, true);
					} else if (args[1].contains("-")) {
						addRemoveModeChannel(args, chan, conn, false);
					}
				}
			}
		}

	}

	private void modifyChannelMode(String[] args, Channel chan, User user, boolean add) {
		String[] temp;
		if (add) {
			temp = args[1].split("\\+");
		} else {
			temp = args[1].split("\\-");
		}
		List<ChannelMode> modes = new ArrayList<ChannelMode>();
		char[] modesChar = temp[1].toCharArray();
		for (int i = 0; i < modesChar.length; i++) {
			ChannelMode mode = ChannelMode.get(String.valueOf(modesChar[i]));
			if (mode != null) {
				modes.add(mode);
			}
		}
		chan.changeMode(user, modes, add);
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
			ChannelUserMode mode = ChannelUserMode.get(String.valueOf(modes[i]));
			User user = Server.getServer().getUserHandler().getUser(args[i + 2]);
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
