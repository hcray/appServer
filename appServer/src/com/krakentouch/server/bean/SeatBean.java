package com.krakentouch.server.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Seat")
@XmlType(name = "SeatBean", propOrder = { "stageSN", "playerID", "seatIndex"})
public class SeatBean {
	
	@XmlElement(name="StageSN", required = true)
	private int stageSN;//<StageSN>1</StageSN>
	
	@XmlElement(name="PlayerID", required = true)
	private String playerID;//<PlayerID>CDEFGHIJKL</PlayerID>
	
	@XmlElement(name="SeatIndex", required = true)
	private int seatIndex;//<SeatIndex>0<SeatIndex>

	public int getStageSN() {
		return stageSN;
	}

	public void setStageSN(int stageSN) {
		this.stageSN = stageSN;
	}

	public String getPlayerID() {
		return playerID;
	}

	public void setPlayerID(String playerID) {
		this.playerID = playerID;
	}

	public int getSeatIndex() {
		return seatIndex;
	}

	public void setSeatIndex(int seatIndex) {
		this.seatIndex = seatIndex;
	}

}
