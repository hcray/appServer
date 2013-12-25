package com.krakentouch.server.filter;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerCodeFactory implements ProtocolCodecFactory {
	private static final Logger LOG = LoggerFactory.getLogger(ServerCodeFactory.class);
	
	private final ServerEncoder encoder;
	private final ServerDecoder decoder;
	/* final static char endchar = 0x1a; */
	// final static char endchar = 0x0d;

	public ServerCodeFactory() {
		this(Charset.forName("UTF-8"));
		LOG.debug("ServerCodeFactory()");
	}

	public ServerCodeFactory(Charset charset) {
		LOG.debug("ServerCodeFactory(Charset charset):" + charset);
		encoder = new ServerEncoder();
		decoder = new ServerDecoder();
	}

	@Override
	public ProtocolDecoder getDecoder(IoSession iosession) throws Exception {
		
		return decoder;
	}

	@Override
	public ProtocolEncoder getEncoder(IoSession iosession) throws Exception {
		
		return encoder;
	}
}
