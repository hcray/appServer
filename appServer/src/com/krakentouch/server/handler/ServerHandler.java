package com.krakentouch.server.handler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.logging.MdcInjectionFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.krakentouch.server.action.MainAction;
import com.krakentouch.server.service.UserService;

public class ServerHandler extends IoHandlerAdapter {
	// 日志
	private static final Logger LOG = LoggerFactory.getLogger(ServerHandler.class);
	//session集合
    private final Set<IoSession> sessions = Collections.synchronizedSet(new HashSet<IoSession>());
    //用户集合
    private final Set<String> users = Collections.synchronizedSet(new HashSet<String>());
	
	private UserService userService;
	
	private MainAction mainAction;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public MainAction getMainAction() {
		return mainAction;
	}

	public void setMainAction(MainAction mainAction) {
		this.mainAction = mainAction;
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		LOG.warn(cause.getMessage());
		cause.printStackTrace();
		session.close(true);
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		LOG.debug("messageReceived: " + message.toString());
		String user = "";
        sessions.add(session);
        session.setAttribute("user", user);
        MdcInjectionFilter.setProperty(session, "user", user);
		System.out.println("Message : " + message);
		
		String retString = mainAction.doCommand((String)message);
		session.write(retString);
		// If we want to test the write operation, uncomment this line
		//session.write(message);

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
		LOG.debug("session Created...");
		
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
