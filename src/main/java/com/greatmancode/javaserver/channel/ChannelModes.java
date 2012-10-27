package com.greatmancode.javaserver.channel;

import java.util.HashMap;
import java.util.Map;

public enum ChannelModes {

	AWAY("a"), INVISIBLE("i"), GLOBAL_OPERATOR("o"), LOCAL_OPERATOR("O"), REGISTERED("r"), SERVER_NOTICES("s"), WALLOPS("w"), SERVICE("S");

	private static final Map<String, ChannelModes> NAME_MAP = new HashMap<String, ChannelModes>();
	
	private final String mode;

	private ChannelModes(String mode) {
		this.mode = mode;
	}

	public String getMode() {
		return mode;
	}

	public static ChannelModes get(String name) {
		return NAME_MAP.get(name);
	}
	
	@Override
	public String toString() {
		return mode;
	}
	
	static {
		for (ChannelModes type : ChannelModes.values()) {
			NAME_MAP.put(type.getMode(), type);
		}
	}
}
