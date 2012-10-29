package com.greatmancode.javaserver.event.events.user;

import com.greatmancode.javaserver.event.Event;
import com.greatmancode.javaserver.user.User;

/**
 * This event is launched when the user is authed (After initial NICK & USER commands)
 * 
 * @author greatman
 * 
 */
public class UserAuthedEvent extends Event {

	private final User user;

	public UserAuthedEvent(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
}
