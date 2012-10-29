package com.greatmancode.javaserver.event.events.channel;

import com.greatmancode.javaserver.channel.Channel;
import com.greatmancode.javaserver.event.Cancellable;
import com.greatmancode.javaserver.event.Event;
import com.greatmancode.javaserver.user.User;

public class ChannelTopicChangeEvent extends Event implements Cancellable {

	private User user;
	private Channel channel;
	private String topic;

	public ChannelTopicChangeEvent(Channel channel, User changer, String topic) {
		this.channel = channel;
		this.topic = topic;
		this.user = changer;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		super.setCancelled(cancelled);
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
