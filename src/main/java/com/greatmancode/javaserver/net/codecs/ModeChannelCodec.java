package com.greatmancode.javaserver.net.codecs;

import java.util.List;

import com.greatmancode.javaserver.channel.ChannelMode;
import com.greatmancode.javaserver.net.Codec;

public class ModeChannelCodec extends Codec {

	private final String userChannel;
	private final List<ChannelMode> modes;

	public ModeChannelCodec(String userChannel, List<ChannelMode> modes) {
		this.userChannel = userChannel;
		this.modes = modes;
	}

	public String encode() {
		StringBuilder string = new StringBuilder();
		string.append(PREFIX);
		string.append("MODE").append(" ");
		string.append(userChannel).append(" ");
		string.append("+");
		for (ChannelMode mode : modes) {
			string.append(mode);
		}
		
		return string.toString();
	}
}
