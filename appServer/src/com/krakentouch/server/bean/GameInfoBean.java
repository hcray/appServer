package com.krakentouch.server.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Game")
@XmlType(name = "GameInfoBean", propOrder = { "GameID", "nickName", "interDesk", "mode", "valid"})
public class GameInfoBean {
	@XmlElement(name = "GameID", required = true)
	private String GameID;		//  <GameID>abcdef</GameID>
	
	@XmlElement(name = "Nickname", required = true)
	private String nickName;	// <Nickname>某游戏1</Nickname>
	
	@XmlElement(name = "Interdesk", required = true)
	private boolean interDesk;	//  <Interdesk>false</Interdesk>  
	
	@XmlElement(name = "Mode", required = true)
	private int mode;			//  <Mode>1</Mode>
	
	@XmlElement(name = "Valid", required = true)
	private boolean valid;	    //    <Valid>true</Valid>

	public String getGameID() {
		return GameID;
	}

	public void setGameID(String gameID) {
		GameID = gameID;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public boolean isInterDesk() {
		return interDesk;
	}

	public void setInterDesk(boolean interDesk) {
		this.interDesk = interDesk;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
}
