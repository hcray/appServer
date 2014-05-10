package com.krakentouch.server.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="value")
@XmlType(name = "LoginWriteBeanValue", propOrder = {"sn","deskId", "script", "time"})
public class LoginWriteBeanValue {
	@XmlElement(name="SN", required = true)
	int sn;//<Status>0</Status>
	
	@XmlElement(name="DeskID", required = true)
	String deskId;//SN=0;Time=??????????;DeskID=00000000;Script
	
	@XmlElement(name="Script", required = true)
	String script;//<Mode>1</Mode>
	
	@XmlElement(name="Time", required = true)
	String time;//<Status>0</Status>

	public int getSn() {
		return sn;
	}

	public void setSn(int sn) {
		this.sn = sn;
	}

	public String getDeskId() {
		return deskId;
	}

	public void setDeskId(String deskId) {
		this.deskId = deskId;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
