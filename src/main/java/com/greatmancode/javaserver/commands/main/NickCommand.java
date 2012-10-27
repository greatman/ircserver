package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.Server;
import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.net.codecs.NicknameInUseCodec;
import com.greatmancode.javaserver.user.User;

public class NickCommand implements Command {

	public void run(User conn, String[] args) {
		User user = Server.getUserHandler().getUser(args[0]);
		if (user == null) {
			conn.setNickname(args[0]);
		} else {
			conn.send(new NicknameInUseCodec(args[0]));
		}
	}
}
