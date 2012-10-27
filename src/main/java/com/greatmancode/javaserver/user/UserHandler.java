package com.greatmancode.javaserver.user;

import java.util.ArrayList;
import java.util.List;


public class UserHandler {

	private final List<User> userList = new ArrayList<User>();

	public void addUser(User user) {
		if (!userList.contains(user)) {
			userList.add(user);
		}
	}

	public void removeUser(User user) {
		userList.remove(user);
	}

	public int size() {
		return userList.size();
	}

	public User getUser(String username) {
		User conn = null;
		for (User entry : userList) {
			if (entry.getNickname() != null && entry.getNickname().contains(username)) {
				conn = entry;
				break;
			}
		}
		return conn;
	}

}
