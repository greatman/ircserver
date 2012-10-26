package com.greatmancode.javaserver;

import java.util.HashMap;
import java.util.Map;

public enum UserModes {

	AWAY("a"), INVISIBLE("i"), GLOBAL_OPERATOR("o"), LOCAL_OPERATOR("O"), REGISTERED("r"), SERVER_NOTICES("s"), WALLOPS("w"), SERVICE("S");

	private static final Map<String, UserModes> NAME_MAP = new HashMap<String, UserModes>();
	
	private final String mode;

	private UserModes(String mode) {
		this.mode = mode;
	}

	public String getMode() {
		return mode;
	}

	public static UserModes get(String name) {
		return NAME_MAP.get(name);
	}

	public static void main(String[] args) {
		System.out.println(UserModes.get("o"));
		System.out.println(UserModes.get("v"));
		System.out.println(UserModes.get("x"));
	}
	
	@Override
	public String toString() {
		return mode;
	}
	
	static {
		for (UserModes type : UserModes.values()) {
			NAME_MAP.put(type.getMode(), type);
		}
	}
}
