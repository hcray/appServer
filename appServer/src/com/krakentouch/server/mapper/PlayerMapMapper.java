package com.krakentouch.server.mapper;

import com.krakentouch.server.domain.PlayerMap;
import com.krakentouch.server.domain.PlayerScore;

public interface PlayerMapMapper {
	
	public void insertPlayer(PlayerMap playerMap);
	
	public void deletePlayer(PlayerMap playerMap);
	
	public PlayerScore queryScore(String playerID);
	
	public void updatePlayerStatus(PlayerMap playerMap);
	
	public void updatePlayerScore(PlayerScore playerScore);
}
