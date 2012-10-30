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
package com.greatmancode.javaserver.event.events.channel;

import com.greatmancode.javaserver.channel.Channel;
import com.greatmancode.javaserver.channel.ChannelQuitReasons;
import com.greatmancode.javaserver.event.Event;
import com.greatmancode.javaserver.user.User;

public class ChannelUserQuitEvent extends Event {

	private final User user;
	private final Channel channel;
	private final ChannelQuitReasons reason;

	public ChannelUserQuitEvent(User user, Channel channel, ChannelQuitReasons reason) {
		this.user = user;
		this.channel = channel;
		this.reason = reason;
	}

	public User getUser() {
		return user;
	}

	public Channel getChannel() {
		return channel;
	}

	public ChannelQuitReasons getReason() {
		return reason;
	}
}
