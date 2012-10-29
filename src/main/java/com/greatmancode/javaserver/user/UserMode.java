package com.greatmancode.javaserver.user;

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
