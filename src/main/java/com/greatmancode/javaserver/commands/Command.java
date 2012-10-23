package com.greatmancode.javaserver.commands;

import com.greatmancode.javaserver.net.Connection;

public interface Command {

	public void run(Connection conn, String[] args);
}
