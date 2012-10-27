package com.greatmancode.javaserver.net;

import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.greatmancode.javaserver.App;
import com.greatmancode.javaserver.commands.CommandManager;
import com.greatmancode.javaserver.utils.Tools;

public class IRCServerNetHandler extends SimpleChannelUpstreamHandler {

	@Override
	public void handleUpstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
		if (e instanceof ChannelStateEvent) {
			System.out.println(e.toString());
		}
		super.handleUpstream(ctx, e);
	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		ctx.setAttachment(new User(e.getChannel()));
		App.getSessionHandler().addUser((User) ctx.getAttachment());
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		String line = (String) e.getMessage();
		System.out.println("Received this from + " + ((User) ctx.getAttachment()).getNickname() + ": " + line);
		String cmd = Tools.makeNiceCommand(line);
		String[] args = Tools.makeNiceArguments(line);
		CommandManager.run((User) ctx.getAttachment(), cmd, args);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		System.out.println("Unexpected exception from downstream. ");
		e.getCause().printStackTrace();
		e.getChannel().close();
	}
}
