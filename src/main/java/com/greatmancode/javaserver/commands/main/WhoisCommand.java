package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.Server;
import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.event.Source;
import com.greatmancode.javaserver.net.codecs.WhoisCodec;
import com.greatmancode.javaserver.net.codecs.WhoisEndCodec;
import com.greatmancode.javaserver.user.User;

public class WhoisCommand implements Command {

	@Override
	public void run(Source source, String[] args) {
		User user = Server.getServer().getUserHandler().getUser(args[0]);
		if (user != null) {
			source.send(new WhoisCodec(user));
			source.send(new WhoisEndCodec(user));
		}
	}
}
