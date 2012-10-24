package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.App;
import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.net.Connection;
import com.greatmancode.javaserver.net.codecs.ModeCodec;

public class ModeCommand implements Command {

	public void run(Connection conn, String[] args) {
		if (args.length == 1) {
			if (App.channelList.containsKey(args[0])) {
				conn.send(new ModeCodec(args[0], App.channelList.get(args[0]).getModes()));
			}
		}
		else {
			
		}

	}

}
