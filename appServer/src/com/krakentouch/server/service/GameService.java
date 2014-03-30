package com.krakentouch.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.krakentouch.server.bean.GameInfoBean;
import com.krakentouch.server.bean.GameInfoBeans;
import com.krakentouch.server.bean.QueryGamesCommand;
import com.krakentouch.server.domain.GameInfo;
import com.krakentouch.server.domain.SeatMap;
import com.krakentouch.server.domain.StageMap;
import com.krakentouch.server.mapper.GameInfoMapper;
import com.krakentouch.server.mapper.SeatMapMapper;
import com.krakentouch.server.mapper.StageMapMapper;
import com.krakentouch.server.tools.JaxbUtil;

public class GameService {
	private GameInfoMapper gameInfoMapper;
	
	private StageMapMapper stageMapMapper;
	
	private SeatMapMapper seatMapMapper;
	
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
	
	/**
	 * 开桌
	 * @param stageMap
	 * @return
	 */
	public StageMap insertStageMap(StageMap stageMap){
		stageMapMapper.insertStageMap(stageMap);
		return stageMap;
	}
	
	/**
	 * 添加座位
	 * @param seatMap
	 * @return
	 */
	public SeatMap insertSeatMap(SeatMap seatMap){
		seatMapMapper.insertSeatMap(seatMap);
		return seatMap;
	}
	
	
	public GameInfoMapper getGameInfoMapper() {
		return gameInfoMapper;
	}

	public void setGameInfoMapper(GameInfoMapper gameInfoMapper) {
		this.gameInfoMapper = gameInfoMapper;
	}

	public StageMapMapper getStageMapMapper() {
		return stageMapMapper;
	}

	public void setStageMapMapper(StageMapMapper stageMapMapper) {
		this.stageMapMapper = stageMapMapper;
	}

	public SeatMapMapper getSeatMapMapper() {
		return seatMapMapper;
	}

	public void setSeatMapMapper(SeatMapMapper seatMapMapper) {
		this.seatMapMapper = seatMapMapper;
	}
	
}
