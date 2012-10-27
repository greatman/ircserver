package com.greatmancode.javaserver;

import java.util.HashMap;
import java.util.Map;

public enum ChannelUserModes {

	OP("o"), VOICED("v");

	private static final Map<String, ChannelUserModes> NAME_MAP = new HashMap<String, ChannelUserModes>();
	
	private final String mode;

	private ChannelUserModes(String mode) {
		this.mode = mode;
	}

	public String getMode() {
		return mode;
	}

	public static ChannelUserModes get(String name) {
		return NAME_MAP.get(name);
	}

	@Override
	public String toString() {
		return mode;
	}
	
	static {
		for (ChannelUserModes type : ChannelUserModes.values()) {
			NAME_MAP.put(type.getMode(), type);
		}
	}
}
