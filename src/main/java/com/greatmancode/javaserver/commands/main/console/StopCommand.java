package com.greatmancode.javaserver.commands.main.console;

import com.greatmancode.javaserver.Server;
import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.event.Source;

public class StopCommand implements Command {

	@Override
	public void run(Source source, String[] args) {
		// TODO Auto-generated method stub
		if (source instanceof Server) {
			//We stop everything.
			System.exit(0);
		}
	}

}
