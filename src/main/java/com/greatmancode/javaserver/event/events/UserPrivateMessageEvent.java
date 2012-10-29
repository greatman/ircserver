package com.greatmancode.javaserver.event.events;

import com.greatmancode.javaserver.event.Cancellable;
import com.greatmancode.javaserver.event.Event;
import com.greatmancode.javaserver.user.User;

public class UserPrivateMessageEvent extends Event implements Cancellable {
	
	private User sender, receiver;
	private String message;
	
	public UserPrivateMessageEvent(User sender, User receiver, String message) {
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
	}
	
	@Override
	public void setCancelled(boolean cancelled) {
		super.setCancelled(cancelled);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
}
