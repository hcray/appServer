package com.krakentouch.server.action.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.mina.core.session.IoSession;

import com.krakentouch.server.action.ConcludeGameAction;
import com.krakentouch.server.bean.CloseStageBean;
import com.krakentouch.server.bean.CloseStageBeanValue;
import com.krakentouch.server.domain.SeatMap;
import com.krakentouch.server.service.GameService;
import com.krakentouch.server.tools.JaxbUtil;
/***
 * 首先由桌主发出
 * <TCP action="ConcludeGame" address="AP" category="Game" value="StageSN=1;PlayerID=ABCDEFGHIJ"></TCP>
 * 服务器端对本游戏桌的所有用户（通过查询SeatMap表获知）发出下行通知：
 * <TCP action="ConcludeGame" address="NT" category="Game" value="StageSN=1"></TCP>
 * @author CYY
 *
 */
public class ConcludeGameActionImpl implements ConcludeGameAction {
	
	private GameService gameService;

	@Override
	public String doCommand(IoSession session, Map<String,String> commandMap) {
		String retStr = null;
		//得到座位的上的用户
		String command = commandMap.get("Command");
		String stageSN = commandMap.get("StageSN");
//		String playerID = commandMap.get("PlayerID");
		
		//需要通知的用户
		Set<String> playerIdSet = new HashSet<String>();
		//得到同桌的玩家
		List<SeatMap> seatList = gameService.querySeatMapByStageSN(stageSN);
		for(SeatMap seat:seatList){
			playerIdSet.add(seat.getPlayerID());
		}
		
		
		CloseStageBean closeStageBean = new CloseStageBean();
		closeStageBean.setCommand(command);
		closeStageBean.setNote("success");
		closeStageBean.setResult("1");
		
		CloseStageBeanValue closeStageBeanValue = new CloseStageBeanValue();
		closeStageBeanValue.setStageSN(stageSN);
		closeStageBean.setCloseStageBeanValue(closeStageBeanValue );
		
		retStr = JaxbUtil.convertToXml(closeStageBean, "utf-8");
		//session.write(retStr);
		
		Collection<IoSession> sessions = session.getService().getManagedSessions().values();
		for(IoSession s : sessions){
			String tempPlayerId = (String) s.getAttribute("playerId");
			if(playerIdSet.contains(tempPlayerId)){
				s.write(retStr);
			}
		}
		return retStr;
	}

	public GameService getGameService() {
		return gameService;
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}

}
