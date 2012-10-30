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

import com.greatmancode.javaserver.Server;
import com.greatmancode.javaserver.channel.Channel;
import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.user.User;

public class WhoCodec extends Codec {

	private final User conn, connMember;
	private final Channel chan;

	public WhoCodec(User conn, User connMember, Channel chan) {
		this.conn = conn;
		this.chan = chan;
		this.connMember = connMember;
	}

	public String encode() {
		StringBuffer string = new StringBuffer();
		string.append(PREFIX).append("352").append(" ").append(conn.getNickname()).append(" ");
		string.append(chan.getName()).append(" ");
		string.append(connMember.getNickname()).append(" ").append(connMember.getHost()).append(" ");
		string.append(Server.getServer().getServerName()).append(" ").append(connMember.getNickname()).append(" H :0 ").append(connMember.getRealName());
		// TODO: Add version
        return string.toString();
	}

}
