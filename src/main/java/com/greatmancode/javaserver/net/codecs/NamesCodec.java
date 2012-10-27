package com.greatmancode.javaserver.net.codecs;

import java.util.Iterator;
import java.util.Map.Entry;

import com.greatmancode.javaserver.Channel;
import com.greatmancode.javaserver.ChannelUser;
import com.greatmancode.javaserver.ChannelUserModes;
import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.net.User;

public class NamesCodec extends Codec {

	private final User conn;
	private final Channel chan;

	public NamesCodec(User conn, Channel chan) {
		this.conn = conn;
		this.chan = chan;
	}

	public String encode() {
		StringBuffer string = new StringBuffer();
		string.append(PREFIX);
		string.append("353").append(" ");
		string.append(conn.getNickname()).append(" = ").append(chan.getName()).append(" :");
		Iterator<Entry<User, ChannelUser>> iterator = chan.getUserList().entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<User, ChannelUser> entry = iterator.next();
			if (entry.getValue().getUserModes().contains(ChannelUserModes.OP)) {
				string.append("@");
			}
			string.append(entry.getKey().getNickname()).append(" ");
        }
		return string.toString();
	}

}
