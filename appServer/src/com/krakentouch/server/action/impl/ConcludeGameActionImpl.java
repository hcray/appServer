package com.krakentouch.server.action.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.mina.core.session.IoSession;

import com.krakentouch.server.action.ConcludeGameAction;
import com.krakentouch.server.bean.CloseStageBean;
import com.krakentouch.server.bean.CloseStageBeanValue;
import com.krakentouch.server.domain.SeatMap;
import com.krakentouch.server.service.GameService;
import com.krakentouch.server.service.LoginService;
import com.krakentouch.server.tools.JaxbUtil;

public class ConcludeGameActionImpl implements ConcludeGameAction {
	
	private GameService gameService;

	@Override
	public String doCommand(IoSession session, Map<String,String> commandMap) {
		String retStr = null;
		//得到座位的上的用户
		String command = commandMap.get("Command");
		String stageSN = commandMap.get("StageSN");
		String playerID = commandMap.get("PlayerID");
		
		//得到同桌的玩家
		List<SeatMap> seatList = gameService.querySeatMapByStageSN(stageSN);
		Collection<IoSession> sessions = session.getService().getManagedSessions().values();
		for(IoSession s : sessions){
			//session.getAttribute("playerId");
			//TODO 把没个用户的用户id写到session中
		}
		
		CloseStageBean closeStageBean = new CloseStageBean();
		closeStageBean.setCommand(command);
		closeStageBean.setNote("success");
		closeStageBean.setResult("1");
		
		CloseStageBeanValue closeStageBeanValue = new CloseStageBeanValue();
		closeStageBeanValue.setStageSN(stageSN);
		closeStageBean.setCloseStageBeanValue(closeStageBeanValue );
		
		retStr = JaxbUtil.convertToXml(closeStageBean, "utf-8");
		session.write(retStr);
		return retStr;
	}

	public GameService getGameService() {
		return gameService;
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}

}
