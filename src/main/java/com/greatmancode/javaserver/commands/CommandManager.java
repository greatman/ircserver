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
package com.greatmancode.javaserver.commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.greatmancode.javaserver.Server;
import com.greatmancode.javaserver.user.User;
import com.greatmancode.javaserver.util.exceptions.command.CommandAlreadyRegisteredException;

public final class CommandManager {
	
	private final Map<String, Command> commandManager = new HashMap<String, Command>();
	
	public void run(User conn, String command, String[] args) {
		Server.getServer().getLogger().info(command + " : " + Arrays.toString(args));
		if (commandManager.containsKey(command.toUpperCase())) {
			commandManager.get(command.toUpperCase()).run(conn, args);
		}
	}
	
	/**
	 * Register a command. The command name will be 
	 * @param commandName
	 * @param command
	 * @throws CommandAlreadyRegisteredException Thrown if the command already exists in the system.
	 * @throws IllegalArgumentException Thrown if commandName/command is null or commandName equals a empty string.
	 */
	public void registerCommand(String commandName, Command command) {
		if (commandName != null && !commandName.equals("") && command != null) {
			if (!commandManager.containsKey(commandName.toUpperCase())) {
				commandManager.put(commandName.toUpperCase(), command);
			} else {
				throw new CommandAlreadyRegisteredException();
			}
		} else {
			throw new IllegalArgumentException();
		}
		
	}
}
