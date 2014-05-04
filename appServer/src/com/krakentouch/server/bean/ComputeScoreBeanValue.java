package com.krakentouch.server.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="value")
@XmlType(name = "ComputeScoreBeanValue", propOrder = {"playerID", "score", "money", "prop0" })
public class ComputeScoreBeanValue {
	@XmlElement(name="PlayerID", required = true)
	private String playerID; // <PlayerID>ABCDEFGHIJ</PlayerID>
	
	@XmlElement(name="Score", required = true)
	private String score; // PlayerID=ABCDEFGHIJ;Score=0;Money=0;Prop0
	
	@XmlElement(name="Money", required = true)
	private String money; // <Status>0</Status>
	
	@XmlElement(name="Prop0", required = true)
	private String prop0; // <GameID></GameID>

	public String getPlayerID() {
		return playerID;
	}

	public void setPlayerID(String playerID) {
		this.playerID = playerID;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getProp0() {
		return prop0;
	}

	public void setProp0(String prop0) {
		this.prop0 = prop0;
	}
}
