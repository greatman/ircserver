package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.event.Source;
import com.greatmancode.javaserver.net.Codec;

public class ListEndChannelCodec extends Codec {

	private Source source;
	public ListEndChannelCodec(Source source ){
		this.source = source;
	}
	@Override
	public String encode() {
		StringBuilder string = new StringBuilder();
		string.append(PREFIX);
		string.append("323").append(" ").append(source.getNickname()).append(" :End of /LIST");
		return string.toString();
	}

}
