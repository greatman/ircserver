package com.greatmancode.javaserver.channel;

import java.util.ArrayList;
import java.util.List;


public class ChannelUser {

	private final List<ChannelUserMode> userModes = new ArrayList<ChannelUserMode>();
	public ChannelUser() {
	}
	
	//TODO: Send a copy.
	public List<ChannelUserMode> getUserModes() {
		return userModes;
	}
}
