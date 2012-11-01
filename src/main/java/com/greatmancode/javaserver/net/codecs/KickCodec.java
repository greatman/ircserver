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

public class KickCodec extends Codec {

	private final Source operator;
	private final User kicked;
	private final Channel chan;
	private final String reason;

	public KickCodec(Source operator, User kicked, Channel chan,
			String reason) {
		this.operator = operator;
		this.kicked = kicked;
		this.chan = chan;
		this.reason = reason;
	}

	@Override
	public String encode() {
		StringBuffer string = new StringBuffer();
		string.append(":").append(operator.getReprensentation()).append(" ");
		string.append("KICK").append(" ").append(chan.getName()).append(" ");
		string.append(kicked.getNickname()).append(" :").append(reason);
		return string.toString();
	}

}
