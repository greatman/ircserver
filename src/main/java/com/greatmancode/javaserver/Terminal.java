package com.greatmancode.javaserver;

import java.io.IOException;

import com.greatmancode.javaserver.utils.Tools;

import jline.ConsoleReader;

public class Terminal {

	public Terminal() {
		ConsoleReader reader;
		try {
			reader = new ConsoleReader();
			reader.setBellEnabled(false);

			String line;
			while ((line = readLine(reader, "")) != null) {
				String command = Tools.makeNiceCommand(line);
				String[] args = Tools.makeNiceArguments(line);
				Server.getServer().getCommandManager().run(Server.getServer(), command, args);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private String readLine(ConsoleReader reader, String promtMessage)
			throws IOException {
		String line = reader.readLine(promtMessage + "\nshell> ");
		return line.trim();
	}
}
