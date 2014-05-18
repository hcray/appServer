package com.krakentouch.server.action.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.mina.core.session.IoSession;

import com.krakentouch.server.action.CloseStageAction;
import com.krakentouch.server.bean.EndStageCommand;
import com.krakentouch.server.bean.EndStageCommandValue;
import com.krakentouch.server.bean.PlayerOnline;
import com.krakentouch.server.bean.PlayerOnlineBean;
import com.krakentouch.server.bean.StartStageCommand;
import com.krakentouch.server.bean.StartStageCommandValue;
import com.krakentouch.server.domain.PlayerMap;
import com.krakentouch.server.domain.SeatMap;
import com.krakentouch.server.domain.StageMap;
import com.krakentouch.server.service.GameService;
import com.krakentouch.server.service.LoginService;
import com.krakentouch.server.tools.JaxbUtil;
import com.krakentouch.server.tools.ServerConstants;
/***
 * 首先由桌主发出<TCP tag="CloseStage" address="OP" category="Game" value="StageSN=1;PlayerID=ABCDEFGHIJ"></TCP>
 * 服务器端在StageMap表删除对应记录并反馈以
 * <TCP tag="CloseStage" address="FB" category="Game" value="StageSN=1;Status=1;HostIndex=2;GameID=abcdef;PlayerID=ABCDEFGHIJ"></TCP>
 * 
 * 并对本游戏桌的所有用户（通过查询SeatMap表获知）以及所有处于本游戏查厅态的用户（通过查询PlayerMap表获知）进行下行通知：
 * <TCP tag="CloseStage" address="NT" category="Game" value="StageSN=1;Status=-1"></TCP>
 * 
 * 对本游戏桌的所有用户（通过查询SeatMap表获知）更新PlayerMap表的Status字段且对其以及所有询众态的用户进行下行通知：
 * <TCP tag="CloseStage" address="NT" category="Account" value="PlayerID=ABCDEFGHIJ;Status=2"></TCP>
 * @author CYY
 *
 */
public class CloseStageActionImpl implements CloseStageAction {
	
	private LoginService loginService;
	
	private GameService gameService;

	@Override
	public String doCommand(IoSession session, Map<String,String> commandMap) {
		String retStr = null;
		
		String command = commandMap.get("action");
		String stageSN = commandMap.get("StageSN");
		String playerId = commandMap.get("PlayerID");
	
		//需要通知的用户
		Set<String> playerIdSet = new HashSet<String>();
		Set<String> playerIdSetNotify = new HashSet<String>();
		//本桌的用户
		List<SeatMap> seatList = gameService.querySeatMapByStageSN(stageSN);
		for(SeatMap seat:seatList){
			playerIdSet.add(seat.getPlayerID());
			playerIdSetNotify.add(seat.getPlayerID());
		}
		
		//删除座位
		SeatMap seatMap = new SeatMap();
		seatMap.setStageSN(Integer.parseInt(stageSN));
		gameService.deleteSeatMap(seatMap);
		
		
		//查询当前桌的数据
		StageMap retStageMap = gameService.queryStageMapByStageSN(stageSN);
		
		StageMap stageMap = new StageMap();
		stageMap.setStageSN(Integer.parseInt(stageSN));
		gameService.deleteStageMap(stageMap);
		
		EndStageCommand endStageCommand = new EndStageCommand();
		endStageCommand.setCommand(command);
		endStageCommand.setResult("1");
		endStageCommand.setNote("success");
		
		EndStageCommandValue endStageCommandValue = new EndStageCommandValue();
		endStageCommandValue.setGameID(retStageMap.getGameID());
		endStageCommandValue.setHostIndex(retStageMap.getHostIndex());
		endStageCommandValue.setPlayerID(playerId);
		endStageCommandValue.setStageSN(retStageMap.getStageSN());
		endStageCommandValue.setStatus(String.valueOf(retStageMap.getStatus()));
		
		endStageCommand.setEndStageCommandValue(endStageCommandValue);
		
		retStr = JaxbUtil.convertToXml(endStageCommand, "utf-8");
		session.write(retStr);
		
	
		
		//查厅状态的用户
		List<PlayerMap> otherUsers = loginService.selectPlayerByStatus(ServerConstants.playerMap_status_queryHall);
		for(PlayerMap player:otherUsers){
			playerIdSet.add(player.getPlayerID());
		}
		
		StartStageCommand startStageCommand = new StartStageCommand();
		startStageCommand.setCommand(command);
		startStageCommand.setResult("1");
		startStageCommand.setNote("success");
		
		StartStageCommandValue startStageCommandValue = new StartStageCommandValue();
		startStageCommandValue.setStageSN(stageSN);
		startStageCommandValue.setStatus(String.valueOf(ServerConstants.stageMap_status_shutDown));
		startStageCommand.setStartStageCommandValue(startStageCommandValue);
		
		String notifyQuery = JaxbUtil.convertToXml(startStageCommand, "utf-8");
		
		Collection<IoSession> sessions = session.getService().getManagedSessions().values();
		for(IoSession s : sessions){
			String tempPlayerId = (String) s.getAttribute("playerId");
			if(playerIdSet.contains(tempPlayerId)){
				s.write(notifyQuery);
			}
		}
		
		//更新玩家状态
		for(SeatMap seat:seatList){
			PlayerMap playerMap = new PlayerMap();
			playerMap.setPlayerID(seat.getPlayerID());
			playerMap.setStatus(ServerConstants.playerMap_status_queryHall);//玩家游戏状态
			loginService.updatePlayerStatus(playerMap);
		}
		
		PlayerOnlineBean playerOnlineBean = new PlayerOnlineBean();
		playerOnlineBean.setCommand(command);
		playerOnlineBean.setResult("1");
		playerOnlineBean.setNote("success");
		
		PlayerOnline playerOnline = new PlayerOnline();
		//playerOnline.setDeskId(deskId);
		//playerOnline.setGameId("null");
		playerOnline.setStatus(String.valueOf(ServerConstants.playerMap_status_queryHall));
		
		
		//询众状态的用户
		List<PlayerMap> queryPlayers = loginService.selectPlayerByStatus(ServerConstants.playerMap_status_queryPlayers);
		for(PlayerMap player:queryPlayers){
			playerIdSetNotify.add(player.getPlayerID());
		}
		
		for(IoSession s : sessions){
			String tempPlayerId = (String) s.getAttribute("playerId");
			if(playerIdSetNotify.contains(tempPlayerId)){
				playerOnline.setPlayerId(tempPlayerId);
				playerOnlineBean.setPlayerOnline(playerOnline);
				String notify = JaxbUtil.convertToXml(playerOnlineBean, "utf-8");
				s.write(notify);
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

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
}
