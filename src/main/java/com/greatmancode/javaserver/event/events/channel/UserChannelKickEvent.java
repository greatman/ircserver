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
import com.greatmancode.javaserver.event.Source;
import com.greatmancode.javaserver.user.User;

public class UserChannelKickEvent extends Event implements Cancellable {

	private User kicked;
	private Source kicker;
	private Channel channel;
	private String reason;

	public UserChannelKickEvent(Source kicker, User kicked, Channel channel, String reason) {
		this.kicker = kicker;
		this.kicked = kicked;
		this.channel = channel;
		this.reason = reason;
	}

	public Source getKicker() {
		return kicker;
	}

	public void setKicker(Source kicker) {
		this.kicker = kicker;
	}

	public User getKicked() {
		return kicked;
	}

	public void setKicked(User kicked) {
		this.kicked = kicked;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		super.setCancelled(cancelled);
	}
}
