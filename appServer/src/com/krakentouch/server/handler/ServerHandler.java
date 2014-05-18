package com.krakentouch.server.handler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
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
    //private final Set<String> users = Collections.synchronizedSet(new HashSet<String>());
	
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
		
		mainAction.doCommand(session, (String)message);
		/*
		Map<String, String> retMap = mainAction.doCommand(session, (String)message);
		String command = retMap.get("command");
		String retString = retMap.get("result");
		if("login".equals(command)){ //登陆
			String user = retMap.get("playerID");
			sessions.add(session);
			session.setAttribute("user", user);
			MdcInjectionFilter.setProperty(session, "user", user);
			System.out.println("Message : " + message);
			broadcast(user + " login....");
		}else if("sendMessage".equals(command)){//聊天
			String receiveMessage = retMap.get("receiveMessage");
			String receiveId = retMap.get("receiveId");
			sendMessage(receiveId, receiveMessage);
			
		}
		//最后写反馈结果
		session.write(retString);
		*/
		// If we want to test the write operation, uncomment this line
		//session.write(message);

	}
	
	public boolean sendMessage(String rescoveId, String message){
		boolean sendRet = false;
		synchronized (sessions) {
			for (IoSession session : sessions) {
				if (session.isConnected()) {
					String user = (String) session.getAttribute("user");
					if(rescoveId.equals(user)){
						session.write(message);
						sendRet = true;
					}
				}
			}
		}
		return sendRet;
	}
	
    public void broadcast(String message) {
        synchronized (sessions) {
            for (IoSession session : sessions) {
                if (session.isConnected()) {
                    session.write(message);
                }
            }
        }
    }

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		LOG.debug("message Sent");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		LOG.debug("session Closed");
		String playerId = (String) session.getAttribute("playerId");
		String message = "<TCP><action>PlayerLogout</action><value><PlayerID>"+playerId+"</PlayerID></value></TCP>";
		mainAction.doCommand(session, (String)message);
		/*
		String user = (String) session.getAttribute("user");
        users.remove(user);
        sessions.remove(session);
        broadcast("The user " + user + " has left the chat session.");
		*/
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
