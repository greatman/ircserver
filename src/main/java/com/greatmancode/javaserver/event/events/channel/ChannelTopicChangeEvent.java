package com.greatmancode.javaserver.event.events.channel;

import com.greatmancode.javaserver.channel.Channel;
import com.greatmancode.javaserver.event.Cancellable;
import com.greatmancode.javaserver.event.Event;
import com.greatmancode.javaserver.event.Source;

public class ChannelTopicChangeEvent extends Event implements Cancellable {

	private Source source;
	private Channel channel;
	private String topic;

	public ChannelTopicChangeEvent(Channel channel, Source source, String topic) {
		this.channel = channel;
		this.topic = topic;
		this.source = source;
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

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}
}
