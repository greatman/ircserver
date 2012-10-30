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

import java.util.List;

import com.greatmancode.javaserver.channel.ChannelMode;
import com.greatmancode.javaserver.net.Codec;

public class ModeChannelCodec extends Codec {

	private final String userChannel;
	private final List<ChannelMode> modes;

	public ModeChannelCodec(String userChannel, List<ChannelMode> modes) {
		this.userChannel = userChannel;
		this.modes = modes;
	}

	public String encode() {
		StringBuilder string = new StringBuilder();
		string.append(PREFIX);
		string.append("MODE").append(" ");
		string.append(userChannel).append(" ");
		string.append("+");
		for (ChannelMode mode : modes) {
			string.append(mode);
		}
		
		return string.toString();
	}
}
