package com.krakentouch.server.mapper;

import com.krakentouch.server.domain.PlayerMap;

public interface PlayerMapMapper {
	
	public void insertPlayer(PlayerMap playerMap);
	
	public void deletePlayer(PlayerMap playerMap);
	
	public int 	queryScore(String playerID);
}
