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

import com.greatmancode.javaserver.event.Source;
import com.greatmancode.javaserver.net.Codec;

public class NickCodec extends Codec {

	private final Source user;
	private final String newNick;

	public NickCodec(Source user, String newNick) {
		this.user = user;
		this.newNick = newNick;
	}

	@Override
	public String encode() {
		StringBuilder string = new StringBuilder();
		string.append(":").append(user.getReprensentation()).append(" NICK ").append(":").append(newNick);
		return string.toString();
	}

}
