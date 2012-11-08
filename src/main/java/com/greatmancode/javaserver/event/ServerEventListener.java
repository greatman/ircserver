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
package com.greatmancode.javaserver.event;

import java.util.List;

import com.greatmancode.javaserver.Server;
import com.greatmancode.javaserver.channel.ChannelMode;
import com.greatmancode.javaserver.channel.ChannelUser;
import com.greatmancode.javaserver.channel.ChannelUserMode;
import com.greatmancode.javaserver.event.events.channel.ChannelModeChangeEvent;
import com.greatmancode.javaserver.event.events.channel.ChannelTopicChangeEvent;
import com.greatmancode.javaserver.event.events.channel.UserChannelKickEvent;
import com.greatmancode.javaserver.event.events.channel.UserChannelMessageEvent;

public class ServerEventListener implements Listener {

	private static boolean initialized = false;

	public ServerEventListener() {
		if (!initialized) {
			Server.getServer().getEventManager().registerEvents(this);
			initialized = true;
		}
	}

	@EventHandler
	public void userChannelMessageEvent(UserChannelMessageEvent event) {
		List<ChannelMode> chanModes = event.getChannel().getModes();
		if (chanModes.contains(ChannelMode.MODERATED)) {
			ChannelUser chanUser = event.getChannel().getUserList().get(event.getSource());
			if (chanUser != null && (!chanUser.getUserModes().contains(ChannelUserMode.VOICED) && !chanUser.getUserModes().contains(ChannelUserMode.OP))) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void channelTopicChangeEvent(ChannelTopicChangeEvent event) {
		ChannelUser chanUser = event.getChannel().getUserList().get(event.getSource());
		if (chanUser != null && !chanUser.getUserModes().contains(ChannelUserMode.OP)) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void userChannelKickEvent(UserChannelKickEvent event) {
		ChannelUser chanUser = event.getChannel().getUserList().get(event.getKicker());
		if (chanUser != null && !chanUser.getUserModes().contains(ChannelUserMode.OP)) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void channelModeChangeEvent(ChannelModeChangeEvent event) {
		ChannelUser chanUser = event.getChannel().getUserList().get(event.getSource());
		if ((chanUser != null && !chanUser.getUserModes().contains(ChannelUserMode.OP))) {
			event.setCancelled(true);
		}
	}
}
