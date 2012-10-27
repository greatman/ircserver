package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.user.User;

public class IsSupportCodec extends Codec {

	private final User user;
	public IsSupportCodec(User user) {
		this.user = user;
	}
	@Override
	public String encode() {
		//TODO: All conf this
		StringBuffer string = new StringBuffer();
		string.append(PREFIX);
		string.append("005").append(" ").append(user.getNickname()).append(" ");
		string.append("PREFIX=(ov)@+").append(" ");
		string.append("CHANTYPES=#").append(" ");
		string.append("CHANLIMIT=#:10").append(" ");
		string.append("NICKLEN=30").append(" ");
		string.append("NETWORK=").append("GmanNetwork").append(" ");
		string.append("CASEMAPPING=rfc1459").append(" ");
		string.append("TOPICLEN=300").append(" ");
		string.append("CHANNELLEN=50").append(" ");
		string.append("KICKLEN=50").append(" ");
		string.append(":are supported by this server");
		return string.toString();
	}

}
