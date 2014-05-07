package com.krakentouch.server.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="value")
@XmlType(name = "PlayerOnline", propOrder = {"playerId", "deskId", "status", "gameID" ,"gender" ,"nickName" ,"icon" ,"grade"})
public class PlayerOnline {
	@XmlElement(name = "PlayerId", required = true)
	private String playerId;	//PM.PLAYERID,
	
	@XmlElement(name = "DeskId", required = true)
	private String deskId;		//PM.DESKID,
	
	@XmlElement(name = "Status", required = true)
	private String status;		//PM.STATUS,
	
	@XmlElement(name = "GameId", required = true)
	private String gameId;		//PM.GAMEID,
	
	@XmlElement(name = "Gender", required = true)
	private String gender;		//PI.GENDER,
	
	@XmlElement(name = "NickName", required = true)
	private String nickName;	//PI.NICKNAME,
	
	@XmlElement(name = "Icon", required = true)
	private String icon;		//PI.ICON,
	
	@XmlElement(name = "Grade", required = true)
	private String grade;		//PI.GRADE
	
	public String getPlayerId() {
		return playerId;
	}
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}
	public String getDeskId() {
		return deskId;
	}
	public void setDeskId(String deskId) {
		this.deskId = deskId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
}
