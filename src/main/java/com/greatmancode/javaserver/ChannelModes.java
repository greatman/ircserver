package com.greatmancode.javaserver;

import java.util.HashMap;

public enum ChannelModes {

	AWAY("a"), INVISIBLE("i"), GLOBAL_OPERATOR("o"), LOCAL_OPERATOR("O"), REGISTERED("r"), SERVER_NOTICES("s"), WALLOPS("w"), SERVICE("S");

	private static HashMap<String, ChannelModes> nameMap = new HashMap<String, ChannelModes>();
	
	private final String mode;

	private ChannelModes(String mode) {
		this.mode = mode;
	}

	public String getMode() {
		return mode;
	}

	public static ChannelModes get(String name) {
		return nameMap.get(name);
	}

	public static void main(String[] args) {
		System.out.println(ChannelModes.get("o"));
		System.out.println(ChannelModes.get("v"));
		System.out.println(ChannelModes.get("x"));
	}
	
	@Override
	public String toString() {
		return mode;
	}
	
	static {
		for (ChannelModes type : ChannelModes.values()) {
			nameMap.put(type.getMode(), type);
		}
	}
}
