package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.net.Connection;

public class ModeCommand implements Command {

	public void run(Connection conn, String[] args) {
		conn.send(":test 324 " + conn.getNickname() + " #test +nt");

	}

}
