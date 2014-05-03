package com.krakentouch.server.action.impl;

import java.util.Map;

import com.krakentouch.server.action.QueryGameAction;
import com.krakentouch.server.service.GameService;

public class QueryGameActionImpl implements QueryGameAction {
	
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
