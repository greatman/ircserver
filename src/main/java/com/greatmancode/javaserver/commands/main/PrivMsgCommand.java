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
import com.greatmancode.javaserver.event.events.user.UserPrivateMessageEvent;
import com.greatmancode.javaserver.net.codecs.NoSuchNicknameChannelCodec;
import com.greatmancode.javaserver.net.codecs.PrivMsgCodec;
import com.greatmancode.javaserver.user.User;

public class PrivMsgCommand implements Command {

	public void run(Source source, String[] args) {
		//Temp fix.
		if (source instanceof User) {
			if (args[0].contains("#")) {
				Channel chan = Server.getServer().getChannelHandler().getChannel(args[0]);
				if (chan != null) {
					chan.sendMessage((User) source, args[1]);
				}
				
			} else {
				User user = Server.getServer().getUserHandler().getUser(args[0]);
				if (user != null) {
					UserPrivateMessageEvent event = (UserPrivateMessageEvent) Server.getServer().getEventManager().callEvent(new UserPrivateMessageEvent((User) source, user, args[1]));
					if (!event.isCancelled()) {
						user.send(new PrivMsgCodec((User) source, user, args[1]));
					}
				} else {
					source.send(new NoSuchNicknameChannelCodec((User) source, args[1]));
				}
			}
		}

	}

}
