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
import com.greatmancode.javaserver.channel.ChannelQuitReasons;
import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.event.Source;
import com.greatmancode.javaserver.user.User;

public class PartCommand implements Command {

	public void run(Source user, String[] args) {
		if (user instanceof User) {
			if (args.length >= 1 && Server.getServer().getChannelHandler().getChannel(args[0]) != null && Server.getServer().getChannelHandler().getChannel(args[0]).getUserList().containsKey(user)) {
				Server.getServer().getChannelHandler().getChannel(args[0]).removeUser((User) user, ChannelQuitReasons.PART);
			}
		}
	}

}
