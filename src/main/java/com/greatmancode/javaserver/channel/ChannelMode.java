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
package com.greatmancode.javaserver.channel;

import java.util.HashMap;
import java.util.Map;

public enum ChannelMode {

	SECRET("s"), INVITE_ONLY("i"), TOPIC_LOCK("t"), NO_EXTERNAL_MESSAGES("n"), MODERATED("m"), LIMIT("l"), BAN("b"), PASSWORDED("k");

	private static final Map<String, ChannelMode> NAME_MAP = new HashMap<String, ChannelMode>();
	
	private final String mode;

	private ChannelMode(String mode) {
		this.mode = mode;
	}

	public String getMode() {
		return mode;
	}

	public static ChannelMode get(String name) {
		return NAME_MAP.get(name);
	}

	@Override
	public String toString() {
		return mode;
	}
	
	static {
		for (ChannelMode type : ChannelMode.values()) {
			NAME_MAP.put(type.getMode(), type);
		}
	}
}
