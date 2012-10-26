package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.net.User;
import com.greatmancode.javaserver.net.codecs.MotdContentCodec;
import com.greatmancode.javaserver.net.codecs.MotdEndCodec;
import com.greatmancode.javaserver.net.codecs.MotdStartCodec;

public class MotdCommand implements Command {

	public void run(User conn, String[] args) {
		conn.send(new MotdStartCodec(conn));
		conn.send(new MotdContentCodec(conn));
		conn.send(new MotdEndCodec(conn));

	}

}
