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

import com.greatmancode.javaserver.Server;
import com.greatmancode.javaserver.channel.Channel;
import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.event.Source;
import com.greatmancode.javaserver.user.User;

public class KickCommand implements Command {

	public void run(Source conn, String[] args) {
		if (args.length >= 2) {
			Channel chan = Server.getServer().getChannelHandler().getChannel(args[0]);
			if (chan != null) {
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
