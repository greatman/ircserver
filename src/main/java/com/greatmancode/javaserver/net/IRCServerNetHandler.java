package com.greatmancode.javaserver.net;

import java.util.logging.Level;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.greatmancode.javaserver.Server;
import com.greatmancode.javaserver.commands.CommandManager;
import com.greatmancode.javaserver.user.User;
import com.greatmancode.javaserver.utils.Tools;

public class IRCServerNetHandler extends SimpleChannelUpstreamHandler {

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
		ctx.setAttachment(new User(e.getChannel()));
		Server.getUserHandler().addUser((User) ctx.getAttachment());
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		String line = (String) e.getMessage();
		String cmd = Tools.makeNiceCommand(line);
		String[] args = Tools.makeNiceArguments(line);
		CommandManager.run((User) ctx.getAttachment(), cmd, args);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		Server.getLogger().log(Level.SEVERE, "Unexpected exception from downstream. ", e.getCause());
	}
}
