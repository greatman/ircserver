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
