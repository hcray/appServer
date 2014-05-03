package com.krakentouch.server.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Value")
@XmlType(name = "CommandBeanValue", propOrder = {"playerID",	"deskID", "status", "gameID" })
public class CommandBeanValue {
	@XmlElement(name="PlayerID", required = true)
	private String playerID; // <PlayerID>ABCDEFGHIJ</PlayerID>
	
	@XmlElement(name="DeskID", required = true)
	private String deskID; // <DeskID>00000000</DeskID>
	
	@XmlElement(name="Status", required = true)
	private String status; // <Status>0</Status>
	
	@XmlElement(name="GameID", required = true)
	private String gameID; // <GameID></GameID>

	public String getPlayerID() {
		return playerID;
	}

	public void setPlayerID(String playerID) {
		this.playerID = playerID;
	}

	public String getDeskID() {
		return deskID;
	}

	public void setDeskID(String deskID) {
		this.deskID = deskID;
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
}
