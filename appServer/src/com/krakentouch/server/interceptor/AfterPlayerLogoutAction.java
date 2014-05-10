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
import com.krakentouch.server.domain.PlayerInfo;
import com.krakentouch.server.domain.PlayerMap;
import com.krakentouch.server.service.LoginService;
import com.krakentouch.server.tools.JaxbUtil;

@Aspect
public class AfterPlayerLogoutAction {
	
	private LoginService loginService;
	
	@Pointcut("execution(* com.krakentouch.server.action.PlayerLogoutAction.*(..))")  
    private void anyMethod(){}//定义一个切入点
	
	@AfterReturning("anyMethod()")  
	public void afterReturning(JoinPoint point) {
		Object[] args = point.getArgs();
		IoSession session = (IoSession) args[0];
		@SuppressWarnings("unchecked")
		Map<String,String> commandMap = (Map<String, String>) args[1];
		String playerId = commandMap.get("PlayerID");
		String deskId = commandMap.get("DeskID");
		String command = commandMap.get("action");
		
		PlayerInfo playerInfo = loginService.queryPlayerInfoById(playerId);
		
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
		playerOnline.setDeskId(deskId);
		playerOnline.setGameId("null");
		playerOnline.setPlayerId(playerId);
		playerOnline.setStatus("0");
		String nickName = playerInfo.getNickname();
		playerOnline.setNickName(nickName);
		String gender = String.valueOf(playerInfo.getGender());
		playerOnline.setGender(gender);
		String grade = String.valueOf(playerInfo.getGrade());
		playerOnline.setGrade(grade);
		String icon = String.valueOf(playerInfo.getIcon());
		playerOnline.setIcon(icon);
		
		playerOnlineBean.setPlayerOnline(playerOnline);
		
		
		String notify = JaxbUtil.convertToXml(playerOnlineBean, "utf-8");
		
		Collection<IoSession> sessions = session.getService().getManagedSessions().values();
		for(IoSession s : sessions){
			String tempPlayerId = (String) s.getAttribute("playerId");
			if(playerIdList.contains(tempPlayerId)){
				s.write(notify);
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
