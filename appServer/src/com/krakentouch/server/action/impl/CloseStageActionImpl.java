package com.krakentouch.server.action.impl;

import java.util.List;
import java.util.Map;

import org.apache.mina.core.session.IoSession;

import com.krakentouch.server.action.CloseStageAction;
import com.krakentouch.server.bean.EndStageCommand;
import com.krakentouch.server.domain.PlayerMap;
import com.krakentouch.server.domain.SeatMap;
import com.krakentouch.server.domain.StageMap;
import com.krakentouch.server.service.GameService;
import com.krakentouch.server.service.LoginService;
import com.krakentouch.server.tools.JaxbUtil;

public class CloseStageActionImpl implements CloseStageAction {
	
	private LoginService loginService;
	
	private GameService gameService;

	@Override
	public String doCommand(IoSession session, Map<String,String> commandMap) {
		String retStr = null;
		//删除座位
		String command = commandMap.get("Command");
		String stageSN = commandMap.get("StageSN");
		String playerID = commandMap.get("PlayerID");
		
		
		SeatMap seatMap = new SeatMap();
		seatMap.setStageSN(Integer.parseInt(stageSN));
		gameService.deleteSeatMap(seatMap);
		//查询当前桌的数据
		StageMap retStageMap = gameService.queryStageMapByStageSN(stageSN);
		
		StageMap stageMap = new StageMap();
		stageMap.setStageSN(Integer.parseInt(stageSN));
		gameService.deleteStageMap(stageMap);
		
		//更新同桌的玩家状态
		List<SeatMap> seatList = gameService.querySeatMapByStageSN(stageSN);
		for(SeatMap sm : seatList){
			String pId = sm.getPlayerID();
			PlayerMap playerMap = new PlayerMap();
			playerMap.setPlayerID(pId);
			playerMap.setStatus(2);//玩家查桌状态
			loginService.updatePlayerStatus(playerMap);
		}
		
		EndStageCommand endStageCommand = new EndStageCommand();
		endStageCommand.setCommand(command);
		endStageCommand.setResult("1");
		endStageCommand.setNote("success");
		endStageCommand.setGameID(retStageMap.getGameID());
		endStageCommand.setHostIndex(retStageMap.getHostIndex());
		endStageCommand.setPlayerID(playerID);
		endStageCommand.setStageSN(retStageMap.getStageSN());
		endStageCommand.setStatus(String.valueOf(retStageMap.getStatus()));
		
		retStr = JaxbUtil.convertToXml(endStageCommand, "utf-8");
		session.write(retStr);
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
