package com.greatmancode.javaserver.user;
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
import java.util.HashMap;
import java.util.Map;

public enum UserMode {

	AWAY("a"), INVISIBLE("i"), GLOBAL_OPERATOR("o"), LOCAL_OPERATOR("O"), REGISTERED("r"), SERVER_NOTICES("s"), WALLOPS("w"), SERVICE("S");

	private static final Map<String, UserMode> NAME_MAP = new HashMap<String, UserMode>();
	
	private final String mode;

	private UserMode(String mode) {
		this.mode = mode;
	}

	public String getMode() {
		return mode;
	}

	public static UserMode get(String name) {
		return NAME_MAP.get(name);
	}
	
	@Override
	public String toString() {
		return mode;
	}
	
	static {
		for (UserMode type : UserMode.values()) {
			NAME_MAP.put(type.getMode(), type);
		}
	}
}
