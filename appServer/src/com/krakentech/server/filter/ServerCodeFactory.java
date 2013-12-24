package com.krakentech.server.filter;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineDecoder;
import org.apache.mina.filter.codec.textline.TextLineEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerCodeFactory implements ProtocolCodecFactory {
	private static final Logger LOG = LoggerFactory.getLogger(ServerCodeFactory.class);
	
	private final TextLineEncoder encoder;
	private final TextLineDecoder decoder;
	/* final static char endchar = 0x1a; */
	// final static char endchar = 0x0d;

	public ServerCodeFactory() {
		this(Charset.forName("UTF-8"));
		LOG.debug("ServerCodeFactory()");
	}

	public ServerCodeFactory(Charset charset) {
		LOG.debug("ServerCodeFactory(Charset charset):" + charset);
		encoder = new TextLineEncoder(charset);
		decoder = new TextLineDecoder(charset);
	}

	@Override
	public ProtocolDecoder getDecoder(IoSession iosession) throws Exception {
		
		return decoder;
	}

	@Override
	public ProtocolEncoder getEncoder(IoSession iosession) throws Exception {
		
		return encoder;
	}

	public int getEncoderMaxLineLength() {
		return encoder.getMaxLineLength();
	}

	public void setEncoderMaxLineLength(int maxLineLength) {
		encoder.setMaxLineLength(maxLineLength);
	}

	public int getDecoderMaxLineLength() {
		return decoder.getMaxLineLength();
	}

	public void setDecoderMaxLineLength(int maxLineLength) {
		decoder.setMaxLineLength(maxLineLength);
	}

}
