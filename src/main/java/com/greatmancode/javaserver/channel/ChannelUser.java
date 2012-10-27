package com.greatmancode.javaserver.channel;

import java.util.ArrayList;
import java.util.List;


public class ChannelUser {

	private final List<ChannelUserModes> userModes = new ArrayList<ChannelUserModes>();
	public ChannelUser() {
	}
	
	public List<ChannelUserModes> getUserModes() {
		return userModes;
	}
}
