package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.net.codecs.LUserChannelsCodec;
import com.greatmancode.javaserver.net.codecs.LUserClientCodec;
import com.greatmancode.javaserver.net.codecs.LUserOpCodec;
import com.greatmancode.javaserver.net.codecs.LUserUnknownCodec;
import com.greatmancode.javaserver.user.User;

public class LUsersCommand implements Command {

	public void run(User conn, String[] args) {
		conn.send(new LUserClientCodec(conn));
		conn.send(new LUserOpCodec(conn));
		conn.send(new LUserUnknownCodec(conn));
		conn.send(new LUserChannelsCodec(conn));
	}

}
