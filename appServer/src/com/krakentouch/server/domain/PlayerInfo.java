package com.krakentouch.server.domain;

/**
 * 玩家数据 
 * @author 21829
 *
 */
public class PlayerInfo {
	private String PlayerID;	//char(10) primary key comment '串号'
	private int Gender;			//int(1) comment '性别枚举（0~2分别表示不确定、男、女）' ,
	private String Nickname; 	//char(16) comment '昵称',
	private int Icon; 			//int(2) comment '头像枚举',
	private int Grade;			//int(5) comment '等级' ,
	private String LogPW;		//char(16) comment '密码串'
	
	public String getPlayerID() {
		return PlayerID;
	}
	public void setPlayerID(String playerID) {
		PlayerID = playerID;
	}
	public int getGender() {
		return Gender;
	}
	public void setGender(int gender) {
		Gender = gender;
	}
	public String getNickname() {
		return Nickname;
	}
	public void setNickname(String nickname) {
		Nickname = nickname;
	}
	public int getIcon() {
		return Icon;
	}
	public void setIcon(int icon) {
		Icon = icon;
	}
	public int getGrade() {
		return Grade;
	}
	public void setGrade(int grade) {
		Grade = grade;
	}
	public String getLogPW() {
		return LogPW;
	}
	public void setLogPW(String logPW) {
		LogPW = logPW;
	}
	
	
}
