package com.greatmancode.javaserver.event.events.channel;

import com.greatmancode.javaserver.channel.Channel;
import com.greatmancode.javaserver.event.Cancellable;
import com.greatmancode.javaserver.event.Event;
import com.greatmancode.javaserver.user.User;

public class UserChannelKickEvent extends Event implements Cancellable {

	private User kicker, kicked;
	private Channel channel;
	private String reason;

	public UserChannelKickEvent(User kicker, User kicked, Channel channel, String reason) {
		this.kicker = kicker;
		this.kicked = kicked;
		this.channel = channel;
		this.reason = reason;
	}

	public User getKicker() {
		return kicker;
	}

	public void setKicker(User kicker) {
		this.kicker = kicker;
	}

	public User getKicked() {
		return kicked;
	}

	public void setKicked(User kicked) {
		this.kicked = kicked;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		super.setCancelled(cancelled);
	}
}
