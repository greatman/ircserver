/*
 * This file is part of javaserver.
 *
 * Copyright (c) 2011-2012,
 * 							${project.organization.name} <${url}/>
 *
 * javaserver is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * javaserver is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with javaserver.  If not, see <http://www.gnu.org/licenses/>.
 */
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
