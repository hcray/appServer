package com.krakentouch.server.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Value")
@XmlType(name = "OpenStageCommand", propOrder = {"stageSN",	"status", "hostIndex", "seatIndex", "playerID" })
public class OpenStageCommandValue {
	@XmlElement(name="StageSN", required = true)
	private String stageSN;// <StageSN>1</StageSN>
	
	@XmlElement(name="Status", required = true)
	private int status;// <Status>0</Status>
	
	@XmlElement(name="HostIndex", required = true)
	private int hostIndex;// <HostIndex>2</HostIndex>
	
	@XmlElement(name="SeatIndex", required = true)
	private int seatIndex;// <SeatIndex>0</SeatIndex>
	
	@XmlElement(name="PlayerID", required = true)
	private String playerID;

	public String getStageSN() {
		return stageSN;
	}

	public void setStageSN(String stageSN) {
		this.stageSN = stageSN;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getHostIndex() {
		return hostIndex;
	}

	public void setHostIndex(int hostIndex) {
		this.hostIndex = hostIndex;
	}

	public int getSeatIndex() {
		return seatIndex;
	}

	public void setSeatIndex(int seatIndex) {
		this.seatIndex = seatIndex;
	}

	public String getPlayerID() {
		return playerID;
	}

	public void setPlayerID(String playerID) {
		this.playerID = playerID;
	}

}
