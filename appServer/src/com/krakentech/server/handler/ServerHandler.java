package com.krakentech.server.handler;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerHandler extends IoHandlerAdapter {
	// 日志
	private static final Logger LOG = LoggerFactory
			.getLogger(ServerHandler.class);

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		LOG.debug(cause.getMessage());
		cause.printStackTrace();
		session.close(true);
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		LOG.debug("messageReceived: " + message.toString());
		
		System.out.println("Message : " + ((IoBuffer) message).getInt());

		((IoBuffer) message).flip();

		// If we want to test the write operation, uncomment this line
		session.write(message);

	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		LOG.debug("message Sent");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		LOG.debug("session Closed");
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		LOG.debug("session Created");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		LOG.debug("session Idle");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		LOG.debug("session Opened");
	}

}
