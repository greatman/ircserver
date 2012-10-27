package com.greatmancode.javaserver.commands;

import com.greatmancode.javaserver.user.User;

/**
 * Represents a command
 * @author greatman
 *
 */
public interface Command {

	/**
	 * Run the command
	 * @param user The user than ran this command.
	 * @param args The arguments sent by the user.
	 */
	void run(User user, String[] args);
}
