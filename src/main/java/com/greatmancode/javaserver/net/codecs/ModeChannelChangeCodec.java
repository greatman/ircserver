package com.greatmancode.javaserver.net.codecs;

import java.util.List;

import com.greatmancode.javaserver.channel.Channel;
import com.greatmancode.javaserver.channel.ChannelMode;
import com.greatmancode.javaserver.event.Source;
import com.greatmancode.javaserver.net.Codec;

public class ModeChannelChangeCodec extends Codec {

	private final Source source;
	private final Channel chan;
	private final List<ChannelMode> modes;
	private final boolean add;

	public ModeChannelChangeCodec(Source source, Channel chan, List<ChannelMode> modes, boolean add) {
		this.source = source;
		this.chan = chan;
		this.modes = modes;
		this.add = add;
	}

	@Override
	public String encode() {
		StringBuffer string = new StringBuffer();
		string.append(":").append(source.getReprensentation()).append(" ");
		string.append("MODE").append(" ").append(chan.getName()).append(" ");
		if (add) {
			string.append("+");
		} else {
			string.append("-");
		}
		for (ChannelMode mode : modes) {
			string.append(mode);
		}
		return string.toString();
	}

}
