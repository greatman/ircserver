package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.Channel;
import com.greatmancode.javaserver.ChannelUserModes;
import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.net.User;

public class ModeUserChannelCodec extends Codec {

	private final User changer, user;
	private final Channel chan;
	private final ChannelUserModes mode;
	private final boolean add;
	public ModeUserChannelCodec(User changer, User user, Channel chan, ChannelUserModes mode, boolean add) {
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
