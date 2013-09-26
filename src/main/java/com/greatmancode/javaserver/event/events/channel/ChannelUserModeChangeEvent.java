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
import com.greatmancode.javaserver.channel.ChannelUserMode;
import com.greatmancode.javaserver.event.Cancellable;
import com.greatmancode.javaserver.event.Event;
import com.greatmancode.javaserver.event.Source;
import com.greatmancode.javaserver.user.User;

public class ChannelUserModeChangeEvent extends Event implements Cancellable {

	private Source source;
	private ChannelUserMode mode;
	private User changed;
	private Channel channel;
	public boolean isAdd() {
		return add;
	}

	private final boolean add;
	public ChannelUserModeChangeEvent(Source source, User changed, Channel channel, ChannelUserMode mode, boolean add) {
		this.source = source;
		this.mode = mode;
		this.changed = changed;
		this.channel = channel;
		this.add = add;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public ChannelUserMode getMode() {
		return mode;
	}

	public void setMode(ChannelUserMode mode) {
		this.mode = mode;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		super.setCancelled(cancelled);
	}

	public User getChanged() {
		return changed;
	}

	public void setChanged(User changed) {
		this.changed = changed;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}
}
