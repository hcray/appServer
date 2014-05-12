package com.krakentouch.server.domain;

import java.util.Date;


/**
 * 玩家映射表
 * @author 21829
 *
 */
public class PlayerMap {
	private int ID;			//主键ID
	private String PlayerID;// char(10) comment '串号',
	private String DeskID;	// char(8) comment '串号',
	private int Status;		// int(1) comment '工作状态（-1~3分别表示离开(-1)、待机(0)、询众(1)、查厅(2)、游戏(3)）,
	private String GameID;	// char(6) comment '串号'
	private Date LoginTime; //登录时间
	private Date LogoutTime;//退出时间
	private int DelFlag;	//int(1) '删除标志（0：没有删除；1：删除）'
	
	public int getDelFlag() {
		return DelFlag;
	}
	public void setDelFlag(int delFlag) {
		DelFlag = delFlag;
	}
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
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public Date getLoginTime() {
		return LoginTime;
	}
	public void setLoginTime(Date loginTime) {
		LoginTime = loginTime;
	}
	public Date getLogoutTime() {
		return LogoutTime;
	}
	public void setLogoutTime(Date logoutTime) {
		LogoutTime = logoutTime;
	}
	
}
