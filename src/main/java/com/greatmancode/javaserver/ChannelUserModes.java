package com.greatmancode.javaserver;

import java.util.HashMap;

public enum ChannelUserModes {

	OP("o"), VOICED("v");

	private static HashMap<String, ChannelUserModes> nameMap = new HashMap<String, ChannelUserModes>();
	
	private final String mode;

	private ChannelUserModes(String mode) {
		this.mode = mode;
	}

	public String getMode() {
		return mode;
	}

	public static ChannelUserModes get(String name) {
		return nameMap.get(name);
	}

	public static void main(String[] args) {
		System.out.println(ChannelUserModes.get("o"));
		System.out.println(ChannelUserModes.get("v"));
		System.out.println(ChannelUserModes.get("x"));
	}
	
	@Override
	public String toString() {
		return mode;
	}
	
	static {
		for (ChannelUserModes type : ChannelUserModes.values()) {
			nameMap.put(type.getMode(), type);
		}
	}
}
