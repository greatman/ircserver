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
