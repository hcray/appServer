package com.krakentouch.server.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="value")
@XmlType(name = "value", propOrder = { "deskId", "mode", "status"})
public class StartupBeanValue {
	@XmlElement(name="DeskID", required = true)
	String deskId;//<DeskID>00000000</DeskID>
	
	@XmlElement(name="Mode", required = true)
	int mode;//<Mode>1</Mode>
	
	@XmlElement(name="Status", required = true)
	int status;//<Status>0</Status>

	public String getDeskId() {
		return deskId;
	}

	public void setDeskId(String deskId) {
		this.deskId = deskId;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
