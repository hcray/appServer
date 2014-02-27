package com.krakentouch.server.service;

import java.util.List;

import com.krakentouch.server.domain.GameInfo;
import com.krakentouch.server.mapper.GameInfoMapper;

public class GameService {
	private GameInfoMapper gameInfoMapper;

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
