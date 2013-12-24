package com.krakentech.server.filter;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class ServerCodec extends ProtocolCodecFilter {
	
	private static ProtocolEncoder encoder = new ServerEncoder();
	private static ProtocolDecoder decoder = new ServerDecoder();
	
	public ServerCodec() {
		super(encoder, decoder);
	}
	
}
