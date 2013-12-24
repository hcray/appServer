package com.krakentech.server.filter;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerDecoder implements ProtocolDecoder{
	private static final Logger LOG = LoggerFactory.getLogger(ServerDecoder.class); 
	
	@Override
	public void decode(IoSession arg0, IoBuffer arg1, ProtocolDecoderOutput arg2)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finishDecode(IoSession arg0, ProtocolDecoderOutput arg1)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
