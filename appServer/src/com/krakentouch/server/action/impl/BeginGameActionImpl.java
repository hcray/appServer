package com.krakentouch.server.action.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.mina.core.session.IoSession;

import com.krakentouch.server.action.BeginGameAction;
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

/****
 * 首先由桌主发出
 * <TCP><action>BeginGame</action><value><StageSN>1</StageSN><PlayerID>ABCDEFGHIJ</PlayerID></value></TCP>，
 * 服务器端更新StageMap表的对应记录并反馈以
 * <TCP action="BeginGame" address="FB" category="Game" value="StageSN=1;Status=1"></TCP>
 * 并对本游戏桌的所有用户（通过查询SeatMap表获知）以及所有处于本游戏查厅态的用户（通过查询PlayerMap表获知）进行下行通知：
 * <TCP action="BeginGame" address="NT" category="Game" value="StageSN=1;Status=1"></TCP>
 * 
 * 对本游戏桌的所有用户（通过查询SeatMap表获知）更新PlayerMap表的Status字段且对其以及所有询众态的用户进行下行通知：
 * <TCP action="BeginGame" address="NT" category="Account" value="PlayerID=ABCDEFGHIJ;Status=3"></TCP>等
 * @author CYY
 *
 */
public class BeginGameActionImpl implements BeginGameAction {
	
	private LoginService loginService;
	
	private GameService gameService;

	@Override
	public String doCommand(IoSession session, Map<String,String> commandMap) {
		String retStr = null;
		String command = commandMap.get("Command");
		String stageSN = commandMap.get("StageSN");
		//String status = commandMap.get("Status");
		String playerId = commandMap.get("PlayerID");
		
		//更新座位状态
		StageMap stageMap = new StageMap();
		stageMap.setStageSN(Integer.parseInt(stageSN));
		stageMap.setStatus(ServerConstants.stageMap_status_play);
		gameService.updateStageMap(stageMap);
		
		StartStageCommand startStageCommand = new StartStageCommand();
		startStageCommand.setCommand(command);
		startStageCommand.setResult("1");
		startStageCommand.setNote("success");
		
		StartStageCommandValue startStageCommandValue = new StartStageCommandValue();
		startStageCommandValue.setStageSN(stageSN);
		startStageCommandValue.setStatus(String.valueOf(ServerConstants.playerMap_status_queryPlayers));
		startStageCommand.setStartStageCommandValue(startStageCommandValue);
		
		retStr = JaxbUtil.convertToXml(startStageCommand, "utf-8");
		session.write(retStr);

		//需要通知的用户
		Set<String> playerIdSet = new HashSet<String>();
		Set<String> playerIdSetNotify = new HashSet<String>();
		//本桌的用户
		List<SeatMap> seatList = gameService.querySeatMapByStageSN(stageSN);
		for(SeatMap seat:seatList){
			playerIdSet.add(seat.getPlayerID());
			playerIdSetNotify.add(seat.getPlayerID());
		}
		
		//查厅状态的用户
		List<PlayerMap> otherUsers = loginService.selectPlayerByStatus(ServerConstants.playerMap_status_queryHall);
		for(PlayerMap player:otherUsers){
			playerIdSet.add(player.getPlayerID());
		}
		
		Collection<IoSession> sessions = session.getService().getManagedSessions().values();
		for(IoSession s : sessions){
			String tempPlayerId = (String) s.getAttribute("playerId");
			if(playerIdSet.contains(tempPlayerId)){
				s.write(retStr);
			}
		}
		
		//更新玩家状态
		for(SeatMap seat:seatList){
			PlayerMap playerMap = new PlayerMap();
			playerMap.setPlayerID(seat.getPlayerID());
			playerMap.setStatus(ServerConstants.playerMap_status_play);//玩家游戏状态
			loginService.updatePlayerStatus(playerMap);
		}
		
		PlayerOnlineBean playerOnlineBean = new PlayerOnlineBean();
		playerOnlineBean.setCommand(command);
		playerOnlineBean.setResult("1");
		playerOnlineBean.setNote("success");
		
		PlayerOnline playerOnline = new PlayerOnline();
		//playerOnline.setDeskId(deskId);
		//playerOnline.setGameId("null");
		playerOnline.setPlayerId(playerId);
		playerOnline.setStatus(String.valueOf(ServerConstants.playerMap_status_play));
		
		playerOnlineBean.setPlayerOnline(playerOnline);
		
		String notify = JaxbUtil.convertToXml(playerOnlineBean, "utf-8");
		
		//询众状态的用户
		List<PlayerMap> queryPlayers = loginService.selectPlayerByStatus(ServerConstants.playerMap_status_queryPlayers);
		for(PlayerMap player:queryPlayers){
			playerIdSetNotify.add(player.getPlayerID());
		}
		
		for(IoSession s : sessions){
			String tempPlayerId = (String) s.getAttribute("playerId");
			if(playerIdSetNotify.contains(tempPlayerId)){
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
