package com.krakentouch.server.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Stage")
@XmlType(name = "StageBean", propOrder = { "stageSN", "status" , "hostIndex", "gameID"})
public class StageBean {
	
	@XmlElement(name="StageSN", required = true)
	private String stageSN;//<StageSN>1</StageSN>
	
	@XmlElement(name="Status", required = true)
	private String status;//<Status>0</Status>
	
	@XmlElement(name="HostIndex", required = true)
	private String hostIndex;//<HostIndex>0</HostIndex>
	
	@XmlElement(name="GameID", required = true)
	private String gameID;//<GameID>abcdef</GameID>

	public String getStageSN() {
		return stageSN;
	}

	public void setStageSN(String stageSN) {
		this.stageSN = stageSN;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHostIndex() {
		return hostIndex;
	}

	public void setHostIndex(String hostIndex) {
		this.hostIndex = hostIndex;
	}

	public String getGameID() {
		return gameID;
	}

	public void setGameID(String gameID) {
		this.gameID = gameID;
	}
	
}
