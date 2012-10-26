package com.greatmancode.javaserver.commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.greatmancode.javaserver.commands.main.JoinCommand;
import com.greatmancode.javaserver.commands.main.KickCommand;
import com.greatmancode.javaserver.commands.main.LUsersCommand;
import com.greatmancode.javaserver.commands.main.ModeCommand;
import com.greatmancode.javaserver.commands.main.NickCommand;
import com.greatmancode.javaserver.commands.main.PartCommand;
import com.greatmancode.javaserver.commands.main.PingCommand;
import com.greatmancode.javaserver.commands.main.PrivMsgCommand;
import com.greatmancode.javaserver.commands.main.QuitCommand;
import com.greatmancode.javaserver.commands.main.TopicCommand;
import com.greatmancode.javaserver.commands.main.UserCommand;
import com.greatmancode.javaserver.commands.main.WhoCommand;
import com.greatmancode.javaserver.net.User;

public class CommandManager {

	private CommandManager() {
		
	}
	
	private static final Map<String, Command> COMMAND_LIST = new HashMap<String, Command>();
	public static void run(User conn, String command, String[] args) {
		System.out.println(command + " : " + Arrays.toString(args));
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
	}
}
