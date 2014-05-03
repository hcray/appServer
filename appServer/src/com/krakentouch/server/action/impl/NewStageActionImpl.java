package com.krakentouch.server.action.impl;

import java.util.Map;

import com.krakentouch.server.action.NewStageAction;
import com.krakentouch.server.bean.OpenStageCommand;
import com.krakentouch.server.bean.OpenStageCommandValue;
import com.krakentouch.server.domain.PlayerMap;
import com.krakentouch.server.domain.SeatMap;
import com.krakentouch.server.domain.StageMap;
import com.krakentouch.server.service.GameService;
import com.krakentouch.server.service.LoginService;
import com.krakentouch.server.tools.JaxbUtil;

public class NewStageActionImpl implements NewStageAction {
	
	private LoginService loginService;
	
	private GameService gameService;

	@Override
	public String doCommand(Map<String,String> commandMap) {
		String retStr = null;
		String gameID = commandMap.get("GameID");
		String hostIndex = commandMap.get("HostIndex");
		String playerID = commandMap.get("PlayerID");
		
		//加入桌子
		StageMap stageMap = new StageMap();
		stageMap.setGameID(gameID);
		stageMap.setHostIndex(Integer.parseInt(hostIndex));
		stageMap.setStatus(0);//0:待机 1：游戏
		gameService.insertStageMap(stageMap);
		
		//加入位子
		SeatMap seatMap = new SeatMap();
		seatMap.setPlayerID(playerID);
		seatMap.setSeatIndex(0);//座位序号 为 0 开桌的默认的设置为第一个座位 0
		int stageSN = stageMap.getStageSN();
		seatMap.setStageSN(stageSN);
		gameService.insertSeatMap(seatMap);

		//更新玩家状态
		PlayerMap playerMap = new PlayerMap();
		playerMap.setPlayerID(playerID);
		playerMap.setStatus(3); //游戏状态 ???? 在位子上，没有开始游戏
		loginService.updatePlayerStatus(playerMap);
		
		//返回的字符串
		OpenStageCommand openStageCommand = new OpenStageCommand();
		OpenStageCommandValue openStageCommandValue = new OpenStageCommandValue(); 
		
		openStageCommand.setCommand(commandMap.get("Command"));
		openStageCommand.setResult("1");
		openStageCommand.setNote("success");
		
		openStageCommandValue.setStageSN(String.valueOf(stageSN));
		openStageCommandValue.setHostIndex(Integer.parseInt(hostIndex));
		openStageCommandValue.setSeatIndex(0);
		openStageCommandValue.setStatus(3);
		
		openStageCommand.setOpenStageCommand(openStageCommand);
		
		retStr = JaxbUtil.convertToXml(openStageCommand, "utf-8");
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
