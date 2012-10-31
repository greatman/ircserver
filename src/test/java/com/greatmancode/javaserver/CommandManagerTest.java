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
}
