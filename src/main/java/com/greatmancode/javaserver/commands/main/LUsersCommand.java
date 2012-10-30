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
package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.net.codecs.LUserChannelsCodec;
import com.greatmancode.javaserver.net.codecs.LUserClientCodec;
import com.greatmancode.javaserver.net.codecs.LUserOpCodec;
import com.greatmancode.javaserver.net.codecs.LUserUnknownCodec;
import com.greatmancode.javaserver.user.User;

public class LUsersCommand implements Command {

	public void run(User conn, String[] args) {
		conn.send(new LUserClientCodec(conn));
		conn.send(new LUserOpCodec(conn));
		conn.send(new LUserUnknownCodec(conn));
		conn.send(new LUserChannelsCodec(conn));
	}

}
