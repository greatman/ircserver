package com.greatmancode.javaserver.event.events.channel;

import com.greatmancode.javaserver.channel.Channel;
import com.greatmancode.javaserver.channel.ChannelUser;
import com.greatmancode.javaserver.event.Cancellable;
import com.greatmancode.javaserver.event.Event;
import com.greatmancode.javaserver.user.User;

public class UserJoinChannelEvent extends Event implements Cancellable {

	private User user;
	private ChannelUser chanUser;
	private Channel channel;

	public UserJoinChannelEvent(User user, Channel channel, ChannelUser chanUser) {
		this.user = user;
		this.chanUser = chanUser;
		this.channel = channel;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ChannelUser getChanUser() {
		return chanUser;
	}

	public void setChanUser(ChannelUser chanUser) {
		this.chanUser = chanUser;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		super.setCancelled(cancelled);
	}

}
