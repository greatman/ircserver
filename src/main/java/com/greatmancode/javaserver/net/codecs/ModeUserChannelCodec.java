package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.channel.Channel;
import com.greatmancode.javaserver.channel.ChannelUserMode;
import com.greatmancode.javaserver.event.Source;
import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.user.User;

public class ModeUserChannelCodec extends Codec {

	private final Source changer;
	private final User user;
	private final Channel chan;
	private final ChannelUserMode mode;
	private final boolean add;
	public ModeUserChannelCodec(Source changer, User user, Channel chan, ChannelUserMode mode, boolean add) {
		this.changer = changer;
		this.user = user;
		this.chan = chan;
		this.mode = mode;
		this.add = add;
	}
	@Override
	public String encode() {
		StringBuffer string = new StringBuffer();
		string.append(":").append(changer.getReprensentation()).append(" ");
		string.append("MODE").append(" ").append(chan.getName()).append(" ");
		if (add) {
			string.append("+");
		} else {
			string.append("-");
		}
		string.append(mode).append(" ");
		string.append(user.getNickname());
		return string.toString();
	}

}
