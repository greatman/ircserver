package com.greatmancode.javaserver.commands.main;

import com.greatmancode.javaserver.Server;
import com.greatmancode.javaserver.channel.Channel;
import com.greatmancode.javaserver.commands.Command;
import com.greatmancode.javaserver.event.Source;
import com.greatmancode.javaserver.net.codecs.ListChannelCodec;
import com.greatmancode.javaserver.net.codecs.ListEndChannelCodec;
import com.greatmancode.javaserver.net.codecs.ListStartChannelCodec;

public class ListCommand implements Command {

	@Override
	public void run(Source source, String[] args) {
		source.send(new ListStartChannelCodec(source));
		for (Channel channel : Server.getServer().getChannelHandler().getChannelList()) {
			source.send(new ListChannelCodec(source, channel));
		}
		source.send(new ListEndChannelCodec(source));
	}

}
