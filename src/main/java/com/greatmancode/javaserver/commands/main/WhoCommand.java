package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.App;
import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.net.Connection;

public class WhoCommand implements Command {

	public void run(Connection conn, String[] args) {
		for (Connection channelMember : App.channelList.get(args[0]).getUserList())
        {
            conn.send("352 " + conn.getNickname() + " " + args[0]
                    + " " + channelMember.getNickname() + " localhost test " + channelMember.getNickname()
                    + " H :0 Uber desc");
        }

	}

}
