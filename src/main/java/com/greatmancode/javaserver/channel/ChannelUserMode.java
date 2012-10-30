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

public enum ChannelUserMode {

	OP("o"), VOICED("v");

	private static final Map<String, ChannelUserMode> NAME_MAP = new HashMap<String, ChannelUserMode>();
	
	private final String mode;

	private ChannelUserMode(String mode) {
		this.mode = mode;
	}

	public String getMode() {
		return mode;
	}

	public static ChannelUserMode get(String name) {
		return NAME_MAP.get(name);
	}

	@Override
	public String toString() {
		return mode;
	}
	
	static {
		for (ChannelUserMode type : ChannelUserMode.values()) {
			NAME_MAP.put(type.getMode(), type);
		}
	}
}
