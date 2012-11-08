package com.greatmancode.javaserver.net.codecs;

import com.greatmancode.javaserver.event.Source;
import com.greatmancode.javaserver.net.Codec;

public class ListStartChannelCodec extends Codec {

	private Source source;
	public ListStartChannelCodec(Source source ) {
		this.source = source;
	}
	@Override
	public String encode() {
		StringBuilder string = new StringBuilder();
		string.append(PREFIX);
		string.append("321").append(" ").append(source.getNickname()).append(" Channel :Users  Name");
		return string.toString();
	}

}
