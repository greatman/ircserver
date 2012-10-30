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
import com.greatmancode.javaserver.event.Cancellable;
import com.greatmancode.javaserver.event.Event;
import com.greatmancode.javaserver.user.User;

//TODO: Make so channel can be non-final.
public class UserChannelMessageEvent extends Event implements Cancellable{

	private User user;
	private final Channel channel;
	private String message;
	public UserChannelMessageEvent(User user, Channel channel, String message) {
		this.user = user;
		this.channel = channel;
		this.message = message;
	}
	
	@Override
	public void setCancelled(boolean cancelled) {
		super.setCancelled(cancelled);
	}
	
	public Channel getChannel() {
		return channel;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
