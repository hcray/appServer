package com.krakentouch.server.action;

import java.util.Map;

import com.krakentouch.server.service.GameService;

public class QueryGameAction implements BaseAction {
	
	private GameService gameService;

	@Override
	public String doCommand(Map<String,String> commandMap) {
		String retStr = "";
		try{
			retStr = gameService.queryGames(commandMap);
		}catch(Exception e){
			e.printStackTrace();
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
