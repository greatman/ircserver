package com.greatmancode.javaserver.event.events.user;

import com.greatmancode.javaserver.event.Cancellable;
import com.greatmancode.javaserver.event.Event;
import com.greatmancode.javaserver.event.Source;
import com.greatmancode.javaserver.user.User;

public class UserChangeNickEvent extends Event implements Cancellable {

	private User user;
	private Source source;
	private String newName;

	public UserChangeNickEvent(Source source, User user, String newName) {
		this.user = user;
		this.source = source;
		this.newName = newName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		super.setCancelled(cancelled);
	}
}
