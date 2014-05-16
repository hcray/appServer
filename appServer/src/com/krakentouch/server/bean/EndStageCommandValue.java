package com.krakentouch.server.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="value")
@XmlType(name = "EndStageCommandValue", propOrder = {"playerID", "status", "gameID" ,"stageSN" ,"hostIndex"})
public class EndStageCommandValue {
	@XmlElement(name="PlayerID", required = true)
	private String playerID; // <PlayerID>ABCDEFGHIJ</PlayerID>
	
	@XmlElement(name="Status", required = true)
	private String status; // <Status>0</Status>
	
	@XmlElement(name="GameID", required = true)
	private String gameID; // <GameID></GameID>
	
	@XmlElement(name="StageSN", required = true)
	private int stageSN;//<StageSN>1</StageSN>
	
	@XmlElement(name="HostIndex", required = true)
	private int hostIndex;//<HostIndex>2</HostIndex>

	public String getPlayerID() {
		return playerID;
	}

	public void setPlayerID(String playerID) {
		this.playerID = playerID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGameID() {
		return gameID;
	}

	public void setGameID(String gameID) {
		this.gameID = gameID;
	}

	public int getStageSN() {
		return stageSN;
	}

	public void setStageSN(int stageSN) {
		this.stageSN = stageSN;
	}

	public int getHostIndex() {
		return hostIndex;
	}

	public void setHostIndex(int hostIndex) {
		this.hostIndex = hostIndex;
	}
	
}
