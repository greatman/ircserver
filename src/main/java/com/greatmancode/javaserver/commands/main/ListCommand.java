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

import com.greatmancode.javaserver.Server;
import com.greatmancode.javaserver.channel.Channel;
import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.event.Source;
import com.greatmancode.javaserver.net.codecs.ListChannelCodec;
import com.greatmancode.javaserver.net.codecs.ListEndChannelCodec;
import com.greatmancode.javaserver.net.codecs.ListStartChannelCodec;

public class ListCommand implements Command {

	@Override
	public void run(Source source, String[] args) {
		source.send(new ListStartChannelCodec(source));
		for (Channel channel : Server.getServer().getChannelHandler().getChannelList()) {
			source.send(new ListChannelCodec(source, channel));
		}
		source.send(new ListEndChannelCodec(source));
	}

}
