package com.greatmancode.javaserver.event.events.channel;

import java.util.List;

import com.greatmancode.javaserver.channel.Channel;
import com.greatmancode.javaserver.channel.ChannelMode;
import com.greatmancode.javaserver.event.Cancellable;
import com.greatmancode.javaserver.event.Event;
import com.greatmancode.javaserver.event.Source;

public class ChannelModeChangeEvent extends Event implements Cancellable {

	private Source source;
	private Channel channel;
	private List<ChannelMode> modes;
	private boolean add;

	public ChannelModeChangeEvent(Source source, Channel channel, List<ChannelMode> modes, boolean add) {
		this.source = source;
		this.channel = channel;
		this.modes = modes;
		this.add = add;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public List<ChannelMode> getModes() {
		return modes;
	}

	public void setModes(List<ChannelMode> modes) {
		this.modes = modes;
	}

	public boolean isAdd() {
		return add;
	}

	public void setAdd(boolean add) {
		this.add = add;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		super.setCancelled(cancelled);
	}
}
