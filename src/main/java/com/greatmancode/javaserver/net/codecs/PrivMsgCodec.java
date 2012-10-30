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
import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.user.User;

public class PrivMsgCodec extends Codec {

	private final String msg;
	private final User sender, receiver;
	private final Channel chan;
	public PrivMsgCodec(User sender, User receiver, String msg) {
		this.sender = sender;
		this.receiver = receiver;
		this.msg = msg;
		this.chan = null;
	}
	public PrivMsgCodec(User sender, Channel chan, String msg) {
		this.msg = msg;
		this.sender = sender;
		this.chan = chan;
		this.receiver = null;
	}
	@Override
	public String encode() {
		StringBuffer string = new StringBuffer();
		string.append(":").append(sender.getReprensentation()).append(" ");
		string.append("PRIVMSG ");
		if (chan != null) {
			string.append(chan.getName());
		} else {
			string.append(receiver.getNickname());
		}
		string.append(" :").append(msg);
		return string.toString();
		
	}

}
