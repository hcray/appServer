package com.krakentouch.server.domain;

public class Player {
	private String playerID;	//char(10) primary key comment '串号'
	private int gender;			//int(1) comment '性别枚举（0~2分别表示不确定、男、女）' ,
	private String nickName; 	//char(16) comment '昵称',
	private int icon; 			//int(2) comment '头像枚举',
	private int grade;			//int(5) comment '等级' ,
	private String deskID;		// char(8) comment '串号'
	
	public String getPlayerID() {
		return playerID;
	}
	public void setPlayerID(String playerID) {
		this.playerID = playerID;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getIcon() {
		return icon;
	}
	public void setIcon(int icon) {
		this.icon = icon;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getDeskID() {
		return deskID;
	}
	public void setDeskID(String deskID) {
		this.deskID = deskID;
	}
	
}
