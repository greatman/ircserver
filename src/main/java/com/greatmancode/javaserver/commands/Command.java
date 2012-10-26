package com.greatmancode.javaserver.commands;

import com.greatmancode.javaserver.net.User;

public interface Command {

	void run(User conn, String[] args);
}
