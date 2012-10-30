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
import com.greatmancode.javaserver.commands.main.JoinCommand;
import com.greatmancode.javaserver.commands.main.KickCommand;
import com.greatmancode.javaserver.commands.main.LUsersCommand;
import com.greatmancode.javaserver.commands.main.ModeCommand;
import com.greatmancode.javaserver.commands.main.MotdCommand;
import com.greatmancode.javaserver.commands.main.NickCommand;
import com.greatmancode.javaserver.commands.main.NoticeCommand;
import com.greatmancode.javaserver.commands.main.PartCommand;
import com.greatmancode.javaserver.commands.main.PingCommand;
import com.greatmancode.javaserver.commands.main.PrivMsgCommand;
import com.greatmancode.javaserver.commands.main.QuitCommand;
import com.greatmancode.javaserver.commands.main.TopicCommand;
import com.greatmancode.javaserver.commands.main.UserCommand;
import com.greatmancode.javaserver.commands.main.WhoCommand;
import com.greatmancode.javaserver.user.User;

public final class CommandManager {

	private CommandManager() {
		
	}
	
	private static final Map<String, Command> COMMAND_LIST = new HashMap<String, Command>();
	public static void run(User conn, String command, String[] args) {
		Server.getServer().getLogger().info(command + " : " + Arrays.toString(args));
		if (COMMAND_LIST.containsKey(command.toUpperCase())) {
			COMMAND_LIST.get(command.toUpperCase()).run(conn, args);
		}
	}
	
	static {
		COMMAND_LIST.put("USER", new UserCommand());
		COMMAND_LIST.put("NICK", new NickCommand());
		COMMAND_LIST.put("JOIN", new JoinCommand());
		COMMAND_LIST.put("MODE", new ModeCommand());
		COMMAND_LIST.put("WHO", new WhoCommand());
		COMMAND_LIST.put("PRIVMSG", new PrivMsgCommand());
		COMMAND_LIST.put("QUIT", new QuitCommand());
		COMMAND_LIST.put("PING", new PingCommand());
		COMMAND_LIST.put("PART", new PartCommand());
		COMMAND_LIST.put("LUSERS", new LUsersCommand());
		COMMAND_LIST.put("KICK", new KickCommand());
		COMMAND_LIST.put("TOPIC", new TopicCommand());
		COMMAND_LIST.put("MOTD", new MotdCommand());
		COMMAND_LIST.put("NOTICE", new NoticeCommand());
	}
}
