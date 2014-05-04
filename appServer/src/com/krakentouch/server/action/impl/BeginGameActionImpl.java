package com.krakentouch.server.action.impl;

import java.util.Map;

import org.apache.mina.core.session.IoSession;

import com.krakentouch.server.action.BeginGameAction;
import com.krakentouch.server.bean.StartStageCommand;
import com.krakentouch.server.domain.PlayerMap;
import com.krakentouch.server.domain.StageMap;
import com.krakentouch.server.service.GameService;
import com.krakentouch.server.service.LoginService;
import com.krakentouch.server.tools.JaxbUtil;

public class BeginGameActionImpl implements BeginGameAction {
	
	private LoginService loginService;
	
	private GameService gameService;

	@Override
	public String doCommand(IoSession session, Map<String,String> commandMap) {
		String retStr = null;
		String command = commandMap.get("Command");
		String stageSN = commandMap.get("StageSN");
		String status = commandMap.get("Status");
		String playerID = commandMap.get("PlayerID");
		
		//更新座位状态
		StageMap stageMap = new StageMap();
		stageMap.setStageSN(Integer.parseInt(stageSN));
		stageMap.setStatus(Integer.parseInt(status));
		gameService.updateStageMap(stageMap);
		
		//更新玩家状态
		PlayerMap playerMap = new PlayerMap();
		playerMap.setPlayerID(playerID);
		playerMap.setStatus(3);//玩家游戏状态
		loginService.updatePlayerStatus(playerMap);
		
		StartStageCommand startStageCommand = new StartStageCommand();
		startStageCommand.setCommand(command);
		startStageCommand.setResult("1");
		startStageCommand.setNote("success");
		startStageCommand.setStageSN(stageSN);
		startStageCommand.setStatus(status);
		startStageCommand.setPlayerID(playerID);

		retStr = JaxbUtil.convertToXml(startStageCommand, "utf-8");
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
