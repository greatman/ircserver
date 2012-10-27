package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.App;
import com.greatmancode.javaserver.Channel;
import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.net.User;
import com.greatmancode.javaserver.net.codecs.NoSuchNicknameChannelCodec;
import com.greatmancode.javaserver.net.codecs.PrivMsgCodec;

public class PrivMsgCommand implements Command {

	public void run(User conn, String[] args) {
		if (args[0].contains("#")) {
			Channel chan = App.CHANNEL_LIST.get(args[0]);
			String[] message = new String[args.length - 1];
			System.arraycopy(args, 1, message, 0, args.length - 1);
			if (chan != null) {
				chan.sendMessage(conn, args[1]);
			}
		} else {
			User user = App.getUserHandler().getUser(args[0]);
			if (user != null) {
				user.send(new PrivMsgCodec(conn, user, args[1]));
			} else {
				conn.send(new NoSuchNicknameChannelCodec(conn, args[1]));
			}
		}

	}

}
