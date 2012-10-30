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

import java.util.Iterator;
import java.util.Map.Entry;

import com.greatmancode.javaserver.channel.Channel;
import com.greatmancode.javaserver.channel.ChannelUser;
import com.greatmancode.javaserver.channel.ChannelUserMode;
import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.user.User;

public class NamesCodec extends Codec {

	private final User conn;
	private final Channel chan;

	public NamesCodec(User conn, Channel chan) {
		this.conn = conn;
		this.chan = chan;
	}

	public String encode() {
		StringBuffer string = new StringBuffer();
		string.append(PREFIX);
		string.append("353").append(" ");
		string.append(conn.getNickname()).append(" = ").append(chan.getName()).append(" :");
		Iterator<Entry<User, ChannelUser>> iterator = chan.getUserList().entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<User, ChannelUser> entry = iterator.next();
			if (entry.getValue().getUserModes().contains(ChannelUserMode.OP)) {
				string.append("@");
			}
			string.append(entry.getKey().getNickname()).append(" ");
        }
		return string.toString();
	}

}
