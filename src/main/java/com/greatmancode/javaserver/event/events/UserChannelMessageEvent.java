package com.greatmancode.javaserver.event.events;

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
