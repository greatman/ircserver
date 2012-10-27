package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.App;
import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.net.User;
import com.greatmancode.javaserver.net.codecs.NoticeCodec;

public class NoticeCommand implements Command {

	@Override
	public void run(User conn, String[] args) {
		if (args.length == 2) {
			User user = App.getUserHandler().getUser(args[0]);
			if (user != null) {
				user.send(new NoticeCodec(conn, user, args[1]));
			}
		}

	}

}
