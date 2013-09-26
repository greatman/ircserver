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

import org.junit.Test;

import com.greatmancode.javaserver.commands.CommandManager;
import com.greatmancode.javaserver.commands.main.JoinCommand;
import com.greatmancode.javaserver.util.exceptions.command.CommandAlreadyRegisteredException;

public class CommandManagerTest {

	@Test(expected = CommandAlreadyRegisteredException.class)
	public void testCommandAlreadyRegisteredException() {
		CommandManager cmd = new CommandManager();
		cmd.registerCommand("join", new JoinCommand());
		cmd.registerCommand("join", new JoinCommand());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgumentExceptionDoubleNull() {
		CommandManager cmd = new CommandManager();
		cmd.registerCommand(null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgumentExceptionSingleNullEmptyString() {
		CommandManager cmd = new CommandManager();
		cmd.registerCommand("", null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgumentExceptionNullRealCommand() {
		CommandManager cmd = new CommandManager();
		cmd.registerCommand(null, new JoinCommand());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgumentExceptionEmptyStringRealCommand() {
		CommandManager cmd = new CommandManager();
		cmd.registerCommand("", new JoinCommand());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgumentExceptionValidStringNull() {
		CommandManager cmd = new CommandManager();
		cmd.registerCommand("join", null);
	}
}
