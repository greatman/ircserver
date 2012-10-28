package com.greatmancode.javaserver.net.codecs;

import java.util.List;

import com.greatmancode.javaserver.channel.Channel;
import com.greatmancode.javaserver.channel.ChannelMode;
import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.user.User;

public class ModeChannelChangeCodec extends Codec {

	private final User user;
	private final Channel chan;
	private final List<ChannelMode> modes;
	private final boolean add;

	public ModeChannelChangeCodec(User user, Channel chan, List<ChannelMode> modes, boolean add) {
		this.user = user;
		this.chan = chan;
		this.modes = modes;
		this.add = add;
	}

	@Override
	public String encode() {
		StringBuffer string = new StringBuffer();
		string.append(":").append(user.getReprensentation()).append(" ");
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
