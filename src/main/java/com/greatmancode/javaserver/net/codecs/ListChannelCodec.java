package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.channel.Channel;
import com.greatmancode.javaserver.event.Source;
import com.greatmancode.javaserver.net.Codec;

public class ListChannelCodec extends Codec {

	private final Source source;
	private final Channel channel;
	public ListChannelCodec(Source source, Channel channel) {
		this.source = source;
		this.channel = channel;
	}

	@Override
	public String encode() {
		StringBuilder string = new StringBuilder();
		string.append(PREFIX);
		string.append("322").append(" ").append(source.getNickname()).append(" ");
		string.append(channel.getName()).append(" ").append(channel.getUserList().size()).append(" :");
		string.append(channel.getTopic());
		return string.toString();
	}

}
