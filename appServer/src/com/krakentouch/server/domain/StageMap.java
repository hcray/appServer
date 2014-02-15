package com.krakentouch.server.domain;
/**
 * 游戏桌映射表
 * @author 21829
 *
 */
public class StageMap {
	private int StageSN;	// int(10) primary key comment '流水号',
	private int Status;		// int(1) comment'工作状态（0~1分别表示待机、游戏）',
	private int HostIndex;	// int(20) comment'桌主座位序号',
	private String GameID;	// char(6) comment'串号'
	
	public int getStageSN() {
		return StageSN;
	}
	public void setStageSN(int stageSN) {
		StageSN = stageSN;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public int getHostIndex() {
		return HostIndex;
	}
	public void setHostIndex(int hostIndex) {
		HostIndex = hostIndex;
	}
	public String getGameID() {
		return GameID;
	}
	public void setGameID(String gameID) {
		GameID = gameID;
	}
	
}
