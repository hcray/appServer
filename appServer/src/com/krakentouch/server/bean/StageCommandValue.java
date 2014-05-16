package com.krakentouch.server.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="TCP")
@XmlType(name = "JoinStageCommand", propOrder = { "command", "result", "note", "playerID",
		"stageSN", "seatIndex"})
public class StageCommandValue {
	@XmlElement(name = "Command", required = true)
	private String command; // <Command>login</Command>
	
	@XmlElement(name = "Result", required = true)
	private String result; // <Result>1</Result> ----1 成功 0 失败
	
	@XmlElement(name="Note", required = true)
	private String note; // <Note>....</Note> ----备注
	
	@XmlElement(name="StageSN",required = true)
	private String stageSN;//<StageSN>abcdef</StageSN>
	
	@XmlElement(name="PlayerID",required = true)
	private String playerID;//<PlayerID>CDEFGHIJKL</PlayerID>
	
	@XmlElement(name="SeatIndex",required = true)
	private String seatIndex;//<SeatIndex>CDEFGHIJKL</SeatIndex>

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

	public String getStageSN() {
		return stageSN;
	}

	public void setStageSN(String stageSN) {
		this.stageSN = stageSN;
	}

	public String getPlayerID() {
		return playerID;
	}

	public void setPlayerID(String playerID) {
		this.playerID = playerID;
	}

	public String getSeatIndex() {
		return seatIndex;
	}

	public void setSeatIndex(String seatIndex) {
		this.seatIndex = seatIndex;
	}
}
