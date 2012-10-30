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

import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.user.User;

public class LUserOpCodec extends Codec {

	private final User conn;

	public LUserOpCodec(User conn) {
		this.conn = conn;
	}

	@Override
	public String encode() {
		// TODO: Real OP
		StringBuffer string = new StringBuffer();
		string.append(PREFIX);
		string.append("252").append(" ").append(conn.getNickname()).append(" ");
		string.append("0").append(" :").append("IRC Operators online");
		return string.toString();
	}

}
