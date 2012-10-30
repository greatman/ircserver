package com.greatmancode.javaserver.event.events.user;

import com.greatmancode.javaserver.event.Cancellable;
import com.greatmancode.javaserver.event.Event;
import com.greatmancode.javaserver.event.Source;
import com.greatmancode.javaserver.user.User;

public class UserChangeRealnameEvent extends Event implements Cancellable {

	private User user;
	private Source source;
	private String newRealname;

	public UserChangeRealnameEvent(Source source, User user, String newRealname) {
		this.source = source;
		this.user = user;
		this.newRealname = newRealname;
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

	public String getNewRealname() {
		return newRealname;
	}

	public void setNewRealname(String newRealname) {
		this.newRealname = newRealname;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		super.setCancelled(cancelled);
	}
}
