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
