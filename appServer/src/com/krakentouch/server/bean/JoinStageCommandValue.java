package com.krakentouch.server.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="value")
@XmlType(name = "JoinStageCommandValue", propOrder = {"playerID", "stageSN", "seatIndex"})
public class JoinStageCommandValue {
	@XmlElement(name="StageSN",required = true)
	private String stageSN;//<StageSN>abcdef</StageSN>
	
	@XmlElement(name="PlayerId",required = true)
	private String playerID;//<PlayerID>CDEFGHIJKL</PlayerID>
	
	@XmlElement(name="SeatIndex",required = true)
	private String seatIndex;//<SeatIndex>CDEFGHIJKL</SeatIndex>

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
