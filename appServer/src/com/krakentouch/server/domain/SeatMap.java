package com.krakentouch.server.domain;
/**
 * 游戏座位映射表
 * @author 21829
 *
 */
public class SeatMap {
	private int StageSN;	// int(20) comment '流水号',
	private String PlayerID;// char(10) comment '串号',
	private int SeatIndex;	// int(20) comment '座位序号'
	
	public int getStageSN() {
		return StageSN;
	}
	public void setStageSN(int stageSN) {
		StageSN = stageSN;
	}
	public String getPlayerID() {
		return PlayerID;
	}
	public void setPlayerID(String playerID) {
		PlayerID = playerID;
	}
	public int getSeatIndex() {
		return SeatIndex;
	}
	public void setSeatIndex(int seatIndex) {
		SeatIndex = seatIndex;
	}
	
	
}
