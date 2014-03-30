package com.krakentouch.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.krakentouch.server.bean.GameInfoBean;
import com.krakentouch.server.bean.GameInfoBeans;
import com.krakentouch.server.bean.QueryGamesCommand;
import com.krakentouch.server.domain.GameInfo;
import com.krakentouch.server.mapper.GameInfoMapper;
import com.krakentouch.server.tools.JaxbUtil;

public class GameService {
	private GameInfoMapper gameInfoMapper;
	
	/**
	 * 查询游戏
	 * @param commandMap
	 * @return
	 */
	public String queryGames(Map<String,String> commandMap){
		String retStr = null;
		
		List<GameInfo> gameslist = gameInfoMapper.queryAllGame();
		List<GameInfoBean> gameInfoList = new ArrayList<GameInfoBean>();
		for(GameInfo game : gameslist){
			GameInfoBean gameInfoBean = new GameInfoBean();
			gameInfoBean.setGameID(game.getGameID());
			gameInfoBean.setInterDesk(game.isInterDesk());
			gameInfoBean.setMode(game.getMode());
			gameInfoBean.setNickName(game.getNickName());
			gameInfoBean.setValid(game.isValid());
			
			gameInfoList.add(gameInfoBean);
		}
		
		QueryGamesCommand queryGamesCommand = new QueryGamesCommand();
		queryGamesCommand.setCommand(commandMap.get("Command"));
		queryGamesCommand.setResult("1");
		queryGamesCommand.setNote("success");
		GameInfoBeans gameInfoBeans = new GameInfoBeans();
		gameInfoBeans.setGameInfo(gameInfoList);
		queryGamesCommand.setGmeInfoBeans(gameInfoBeans);
		
		retStr = JaxbUtil.convertToXml(queryGamesCommand, "utf-8");
		return retStr;
	}

	/**
	 * 查询所有的游戏
	 * @return
	 */
	public List<GameInfo> queryAllGame(){
		return gameInfoMapper.queryAllGame();
	}
	
	public GameInfoMapper getGameInfoMapper() {
		return gameInfoMapper;
	}

	public void setGameInfoMapper(GameInfoMapper gameInfoMapper) {
		this.gameInfoMapper = gameInfoMapper;
	}
}
