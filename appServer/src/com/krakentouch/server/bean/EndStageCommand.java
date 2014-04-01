package com.krakentouch.server.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="TCP")
@XmlType(name = "CommandBean", propOrder = { "command", "result", "note", "playerID",
		"status", "gameID" ,"stageSN" ,"hostIndex"})
public class EndStageCommand {
	@XmlElement(name = "Command", required = true)
	private String command; // <Command>login</Command>
	
	@XmlElement(name = "Result", required = true)
	private String result; // <Result>1</Result> ----1 成功 0 失败
	
	@XmlElement(name="Note", required = true)
	private String note; // <Note>....</Note> ----备注
	
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

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

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
