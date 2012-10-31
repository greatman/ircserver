/*
 * This file is part of javaserver.
 *
 * Copyright (c) 2011-2012,
 * 							${project.organization.name} <${url}/>
 *
 * javaserver is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * javaserver is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with javaserver.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.greatmancode.javaserver.net;

import java.util.logging.Level;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.greatmancode.javaserver.Server;
import com.greatmancode.javaserver.event.events.NewConnectionEvent;
import com.greatmancode.javaserver.user.User;
import com.greatmancode.javaserver.utils.Tools;

public class IRCServerNetHandler extends SimpleChannelUpstreamHandler {

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
		NewConnectionEvent event = (NewConnectionEvent) Server.getServer().getEventManager().callEvent(new NewConnectionEvent(e.getChannel()));
		if (!event.isCancelled()) {
			ctx.setAttachment(new User(e.getChannel()));
			Server.getServer().getUserHandler().addUser((User) ctx.getAttachment());
		} else {
			event.getChannel().close();
		}
		
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		String line = (String) e.getMessage();
		String cmd = Tools.makeNiceCommand(line);
		String[] args = Tools.makeNiceArguments(line);
		Server.getServer().getCommandManager().run((User) ctx.getAttachment(), cmd, args);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		Server.getServer().getLogger().log(Level.SEVERE, "Unexpected exception from downstream. ", e.getCause());
	}
}
