package com.greatmancode.javaserver.commands.main;

import java.util.Arrays;

import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.net.Connection;
import com.greatmancode.javaserver.net.codecs.MotdContentCodec;
import com.greatmancode.javaserver.net.codecs.MotdEndCodec;
import com.greatmancode.javaserver.net.codecs.MotdStartCodec;
import com.greatmancode.javaserver.net.codecs.MyInfoCodec;
import com.greatmancode.javaserver.net.codecs.WelcomeCodec;

public class UserCommand implements Command {

	public void run(Connection conn, String[] args) {
		System.out.println(Arrays.toString(args));
		conn.setRealName(args[0]);
		conn.setHost(args[2]);
		conn.send(new WelcomeCodec(conn));
				System.out.println("SENT");
		conn.send(new MyInfoCodec(conn));
		System.out.println("SENT");
		
		//TODO: Read motd cmd.
		conn.send(new MotdStartCodec(conn));
		System.out.println("SENT");
		conn.send(new MotdContentCodec(conn));
		System.out.println("SENT");
		conn.send(new MotdEndCodec(conn));
		System.out.println("SENT");

	}

}
