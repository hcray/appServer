package com.krakentouch.server.domain;
/**
 * 玩家映射表
 * @author 21829
 *
 */
public class PlayerMap {
	private String PlayerID;// char(10) comment '串号',
	private String DeskID;	// char(8) comment '串号',
	private int Status;		// int(1) comment '工作状态（0~3分别表示待机、询人、查桌、游戏）',
	private String GameID;	// char(6) comment '串号'
	
	public String getPlayerID() {
		return PlayerID;
	}
	public void setPlayerID(String playerID) {
		PlayerID = playerID;
	}
	public String getDeskID() {
		return DeskID;
	}
	public void setDeskID(String deskID) {
		DeskID = deskID;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public String getGameID() {
		return GameID;
	}
	public void setGameID(String gameID) {
		GameID = gameID;
	}
}
