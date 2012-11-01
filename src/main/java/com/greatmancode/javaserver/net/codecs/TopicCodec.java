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
package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.channel.Channel;
import com.greatmancode.javaserver.event.Source;
import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.user.User;

public class TopicCodec extends Codec {

	private final Source conn;
	private final Channel chan;

	public TopicCodec(Source conn, Channel chan) {
		this.conn = conn;
		this.chan = chan;
	}

	@Override
	public String encode() {
		StringBuffer string = new StringBuffer();
		string.append(":").append(conn.getReprensentation()).append(" ");
		string.append("TOPIC").append(" ").append(chan.getName()).append(" :");
		string.append(chan.getTopic());
		return string.toString();
	}

}
