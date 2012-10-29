package com.greatmancode.javaserver.event.events;

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
