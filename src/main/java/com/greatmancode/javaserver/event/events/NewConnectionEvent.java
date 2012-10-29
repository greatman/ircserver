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
