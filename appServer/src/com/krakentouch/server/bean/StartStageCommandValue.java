package com.krakentouch.server.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="value")
@XmlType(name = "StartStageCommandValue", propOrder = {"stageSN", "status"})
public class StartStageCommandValue {
	
	@XmlElement(name="StageSN",required = true)
	private String stageSN;//<StageSN>abcdef</StageSN>
	
	@XmlElement(name="Status",required = true)
	private String status;//<Status>CDEFGHIJKL</Status>

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

}
