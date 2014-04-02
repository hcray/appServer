package com.krakentouch.server.mapper;

import java.util.List;

import com.krakentouch.server.domain.Player;
import com.krakentouch.server.domain.PlayerMap;
import com.krakentouch.server.domain.PlayerScore;

public interface PlayerMapMapper {
	
	public void insertPlayer(PlayerMap playerMap);
	
	public void deletePlayer(PlayerMap playerMap);
	
	public PlayerScore queryScore(String playerID);
	
	public void updatePlayerStatus(PlayerMap playerMap);
	
	public void updatePlayerScore(PlayerScore playerScore);
	
	public List<Player> queryAllOnlinePlayer();
}
