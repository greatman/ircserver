package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.App;
import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.net.Connection;
import com.greatmancode.javaserver.net.codecs.WhoCodec;

public class WhoCommand implements Command {

	public void run(Connection conn, String[] args) {
		for (Connection channelMember : App.channelList.get(args[0]).getUserList())
        {
			conn.send(new WhoCodec(conn, channelMember, App.channelList.get(args[0])));
        }

	}

}
