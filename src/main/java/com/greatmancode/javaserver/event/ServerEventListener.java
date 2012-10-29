package com.greatmancode.javaserver.event;

import java.util.List;

import com.greatmancode.javaserver.Server;
import com.greatmancode.javaserver.channel.ChannelMode;
import com.greatmancode.javaserver.channel.ChannelUser;
import com.greatmancode.javaserver.channel.ChannelUserMode;
import com.greatmancode.javaserver.event.events.ChannelTopicChangeEvent;
import com.greatmancode.javaserver.event.events.UserChannelMessageEvent;

public class ServerEventListener implements Listener {

	private static boolean initialized = false;

	public ServerEventListener() {
		if (!initialized) {
			Server.getServer().getEventManager().registerEvents(this);
			initialized = true;
		}
	}

	@EventHandler
	public void userChannelMessageEvent(UserChannelMessageEvent event) {
		List<ChannelMode> chanModes = event.getChannel().getModes();
		if (chanModes.contains(ChannelMode.MODERATED)) {
			ChannelUser chanUser = event.getChannel().getUserList().get(event.getUser());
			if (chanUser != null) {
				if (!chanUser.getUserModes().contains(ChannelUserMode.VOICED) && !chanUser.getUserModes().contains(ChannelUserMode.OP)) {
					event.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void channelTopicChangeEvent(ChannelTopicChangeEvent event) {
		ChannelUser chanUser = event.getChannel().getUserList().get(event.getUser());
		if (chanUser != null) {
			if (!chanUser.getUserModes().contains(ChannelUserMode.OP)) {
				System.out.println("HE ISIN'T OP!");
				event.setCancelled(true);
			}
		}
	}
}
