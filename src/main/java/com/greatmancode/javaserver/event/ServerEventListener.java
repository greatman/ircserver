package com.greatmancode.javaserver.event;

import java.util.List;

import com.greatmancode.javaserver.Server;
import com.greatmancode.javaserver.channel.ChannelMode;
import com.greatmancode.javaserver.channel.ChannelUserMode;
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
		System.out.println("ENTERED HERE");
		List<ChannelMode> chanModes = event.getChannel().getModes();
		if (chanModes.contains(ChannelMode.MODERATED) && (!event.getChannel().getUserList().get(event.getUser()).getUserModes().contains(ChannelUserMode.VOICED) && !event.getChannel().getUserList().get(event.getUser()).getUserModes().contains(ChannelUserMode.OP))) {
			event.setCancelled(true);
		}
	}
}
