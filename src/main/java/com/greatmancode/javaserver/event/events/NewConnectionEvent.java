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
package com.greatmancode.javaserver.event.events;

import org.jboss.netty.channel.Channel;

import com.greatmancode.javaserver.event.Cancellable;
import com.greatmancode.javaserver.event.Event;

/**
 * Event for when a new connection to the server is done. Please note that no information about the user is done yet.
 * 
 * Cancelling this event is like not authorizing the connection.
 * 
 * @author greatman
 * 
 */
public class NewConnectionEvent extends Event implements Cancellable {

	private final Channel channel;

	public NewConnectionEvent(Channel channel) {
		this.channel = channel;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		super.setCancelled(cancelled);
	}

	/**
	 * Retrieve the network channel to this connection.
	 * 
	 * @return The network channel.
	 */
	public Channel getChannel() {
		return channel;
	}

}
