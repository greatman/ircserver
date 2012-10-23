package com.greatmancode.javaserver.commands.main;

import java.util.Arrays;

import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.net.Connection;

public class UserCommand implements Command {

	public void run(Connection conn, String[] args) {
		System.out.println(Arrays.toString(args));
		conn.setRealName(args[0]);
		conn.setHost(args[2]);
		conn.send(":test 001 greatman :Welcome to test, a Jircs-powered IRC network.");
		System.out.println("SENT");
		conn.send(":test 004 greatman:greatman server's");
		System.out.println("SENT");
		conn.send(":test 375 greatman: Message of the day");
		System.out.println("SENT");
		conn.send(":test 372 greatman: Content of MOTD");
		System.out.println("SENT");
		conn.send(":test 376 greatman : END of /MOTD command");
		System.out.println("SENT");

	}

}
