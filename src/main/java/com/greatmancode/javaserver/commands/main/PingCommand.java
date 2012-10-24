package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.net.User;
import com.greatmancode.javaserver.net.codecs.PingCodec;

public class PingCommand implements Command {

	public void run(User conn, String[] args) {
		String ping = args[0];
		if (args.length > 1) {
			ping += " :";
			for (int i = 1; i < args.length; i++) {
				ping += args[i];
				if (i < args.length) {
					ping += " ";
				}
			}
		}
		conn.send(new PingCodec(ping));

	}

}
