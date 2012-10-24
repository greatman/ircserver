package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.App;
import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.net.Connection;
import com.greatmancode.javaserver.net.codecs.NicknameInUseCodec;

public class NickCommand implements Command {

	public void run(Connection conn, String[] args) {
		boolean exist = false;
		for (Connection list : App.connectionList) {
			if (list.getNickname() != null && list.getNickname().equals(args[0])) 
			{
				exist = true;
				break;
			}
		}
		if (!exist) {
			conn.setNickname(args[0]);
			
		} else {
			conn.send(new NicknameInUseCodec(args[0]));
		}
		
	}

}
