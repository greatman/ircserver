package com.greatmancode.javaserver.channel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.greatmancode.javaserver.user.User;

public class ChannelHandler {

	private final Map<String, Channel> channelList = new HashMap<String, Channel>();
	
	/**
	 * Adds a new channel in the system. If that channel already exists. It sends the current one.
	 * @param channel The channel name. Example: #lobby
	 * @return The new or current channel instance.
	 */
	public Channel addChannel(String channel) {
		Channel chan = null;
		if (!channelList.containsKey(channel)) {
			chan = new Channel(channel);
			channelList.put(channel, chan);
		} else {
			chan = channelList.get(channel);
		}
		return chan;
	}
	
	/**
	 * Removes a channel from the system.
	 * @param channel The channel name to remove.
	 */
	void removeChannel(String channel) {
		if (channelList.containsKey(channel)) {
			channelList.remove(channel);
		}
	}
	
	/**
	 * Get a Channel. Returns null if the channel doesn't exist.
	 * @param channel The Channel to retrieve
	 * @return The Channel
	 */
	public Channel getChannel(String channel) {
		return channelList.get(channel);
	}
	
	/**
	 * Get the amount of channels formed.
	 * @return The amount of channel formed.
	 */
	public int getSize() {
		return channelList.size();
	}
	
	/**
	 * Retrieve all the channels that the member is in.
	 * @param user The user that we want to retrieve the channels.
	 * @return The list of channel that this user is in.
	 */
	public List<Channel> getUserChannels(User user) {
		List<Channel> userChannelList = new ArrayList<Channel>();
		
		for (Map.Entry<String, Channel> channel : channelList.entrySet()) {
			if (channel.getValue().getUserList().containsKey(this)) {
				userChannelList.add(channel.getValue());
			}
		}
		
		return userChannelList;
	}
}
