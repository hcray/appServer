package com.krakentouch.server.interceptor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.mina.core.session.IoSession;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.krakentouch.server.bean.PlayerOnline;
import com.krakentouch.server.bean.PlayerOnlineBean;
import com.krakentouch.server.domain.PlayerMap;
import com.krakentouch.server.service.LoginService;

@Aspect
public class AfterPlayerLoginAction {
	
	private LoginService loginService;
	
	@Pointcut("execution(* com.krakentouch.server.action.PlayerLoginAction.*(..))")  
    private void anyMethod(){}//定义一个切入点
	
	@AfterReturning("anyMethod()")  
	public void afterReturning(JoinPoint point) {
		Object[] args = point.getArgs();
		IoSession session = (IoSession) args[0];
		Map<String,String> commandMap = (Map<String, String>) args[1];
		String playerID = commandMap.get("PlayerID");
		String deskID = commandMap.get("DeskID");
		String command = commandMap.get("action");
		
		//System.out.println("登录后通知其他的用户");
		List<String> playerIdList = new ArrayList<String>();
		List<PlayerMap> otherUsers = loginService.selectAllQueryStatusPlayer();
		for(PlayerMap player:otherUsers){
			playerIdList.add(player.getPlayerID());
		}
		
		PlayerOnlineBean playerOnlineBean = new PlayerOnlineBean();
		playerOnlineBean.setCommand(command);
		playerOnlineBean.setResult("1");
		playerOnlineBean.setNote("success");
		PlayerOnline playerOnline = new PlayerOnline();
		//TODO
		playerOnlineBean.setPlayerOnline(playerOnline);
		
		Collection<IoSession> sessions = session.getService().getManagedSessions().values();
		for(IoSession s : sessions){
			String playerId = (String) s.getAttribute("playerId");
			if(playerIdList.contains(playerId)){
				s.write("hello i am online");
			}
		}
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
}
