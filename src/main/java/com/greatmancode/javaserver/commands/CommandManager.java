package com.greatmancode.javaserver.commands;

import java.util.Arrays;
import java.util.HashMap;

import com.greatmancode.javaserver.commands.main.JoinCommand;
import com.greatmancode.javaserver.commands.main.ModeCommand;
import com.greatmancode.javaserver.commands.main.NickCommand;
import com.greatmancode.javaserver.commands.main.UserCommand;
import com.greatmancode.javaserver.commands.main.WhoCommand;
import com.greatmancode.javaserver.net.Connection;

public class CommandManager {

	private static final HashMap<String, Command> commandList = new HashMap<String, Command>();
	public static void run(Connection conn, String command, String[] args) {
		System.out.println(command + " : " + Arrays.toString(args));
		if (commandList.containsKey(command)) {
			commandList.get(command).run(conn, args);
		}
	}
	
	static {
		commandList.put("USER", new UserCommand());
		commandList.put("NICK", new NickCommand());
		commandList.put("JOIN", new JoinCommand());
		commandList.put("MODE", new ModeCommand());
		commandList.put("WHO", new WhoCommand());
	}
}
