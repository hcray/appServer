package com.krakentouch.server.service;

import com.krakentouch.server.domain.DeskMap;
import com.krakentouch.server.domain.PlayerMap;
import com.krakentouch.server.mapper.DeskMapMapper;
import com.krakentouch.server.mapper.PlayerMapMapper;

public class LoginService {
	private DeskMapMapper deskMapMapper;

	private PlayerMapMapper PlayerMapMapper;
	
	
	/**
	 * 物理端登录
	 * @param deskMap
	 */
	public void insertDeskMap(DeskMap deskMap){
		deskMapMapper.insertDeskMap(deskMap);
	}
	
	/***
	 * 删除登陆记录
	 * @param deskMap
	 */
	public void deleteDeskMap(DeskMap deskMap){
		deskMapMapper.deleteDeskMap(deskMap);
	}

	
	/***
	 * 用户登陆
	 * @param playerMap
	 */
	public void insertPlayerMap(PlayerMap playerMap){
		PlayerMapMapper.insertPlayer(playerMap);
	}
	
	
	/***
	 * 用户登出
	 * @param playerMap
	 */
	public void deletePlayerMap(PlayerMap playerMap){
		PlayerMapMapper.deletePlayer(playerMap);
	}
	
	
	/***
	 *  查询用户积分
	 * @param playerID
	 * @return 用户积分
	 */
	public int queryScoreByPlayerID(String playerID){
		return PlayerMapMapper.queryScore(playerID);
	}

	public DeskMapMapper getDeskMapMapper() {
		return deskMapMapper;
	}


	public void setDeskMapMapper(DeskMapMapper deskMapMapper) {
		this.deskMapMapper = deskMapMapper;
	}
	
}
