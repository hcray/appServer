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

import com.krakentouch.server.bean.JoinStageCommand;
import com.krakentouch.server.bean.JoinStageCommandValue;
import com.krakentouch.server.domain.PlayerMap;
import com.krakentouch.server.service.LoginService;
import com.krakentouch.server.tools.JaxbUtil;
import com.krakentouch.server.tools.ServerConstants;

/****
 * 
 * 对该用户以及所有处于询众态的用户（通过查询PlayerMap获知）进行下行通知：
 * <TCP>
 * 		<action>QueryAllStage</action>
 * 		<value>
 * 			<PlayerID>ABCDEFGHIJ</PlayerID>
 * 			<Status>2</Status>
 * 		</value>
 * </TCP>
 * @author 21829
 *
 */
@Aspect
public class AfterJoinStageAction {
	
	private LoginService loginService;
	
	@Pointcut("execution(* com.krakentouch.server.action.JoinStageAction.*(..))")  
    private void anyMethod(){}//定义一个切入点
	
	@AfterReturning("anyMethod()")  
	public void afterReturning(JoinPoint point) {
		Object[] args = point.getArgs();
		IoSession session = (IoSession) args[0];
		@SuppressWarnings("unchecked")
		Map<String,String> commandMap = (Map<String, String>) args[1];
		String command = commandMap.get("Command");
		String stageSN = commandMap.get("StageSN");
		String playerID = commandMap.get("PlayerID");
		String seatIndex = commandMap.get("SeatIndex");
		
		List<String> playerIdList = new ArrayList<String>();
		List<PlayerMap> otherUsers = loginService.selectPlayerByStatus(ServerConstants.playerMap_status_queryHall);
		for(PlayerMap player:otherUsers){
			playerIdList.add(player.getPlayerID());
		}
		
		JoinStageCommand joinStageCommand = new JoinStageCommand();
		joinStageCommand.setCommand(command);
		joinStageCommand.setResult("1");
		joinStageCommand.setNote("success");

		JoinStageCommandValue joinStageCommandValue = new JoinStageCommandValue();
		joinStageCommandValue.setPlayerID(playerID);
		joinStageCommandValue.setStageSN(stageSN);
		joinStageCommandValue.setSeatIndex(seatIndex);
		
		joinStageCommand.setJoinStageCommandValue(joinStageCommandValue);
		
		String notify = JaxbUtil.convertToXml(joinStageCommand, "utf-8");
		
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
