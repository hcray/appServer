package com.krakentouch.server.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="value")
@XmlType(name = "ChatMessageBeanValue", propOrder = { "sn", "time", "senderId",
		"recoverId", "memo"})
public class ChatMessageBeanValue {
	@XmlElement(name = "SN", required = true)
	private int sn;//<SN>0</SN>
	
	@XmlElement(name = "Time", required = true)
	private String time;//<Time>xxxxxxxx</Time>
	
	@XmlElement(name = "SenderID", required = true)
	private String senderId;//<SenderID>ABCDEFGHIJ</SenderID>
	
	@XmlElement(name = "RecoverID", required = true)
	private String recoverId;//<RecoverID>CDEFGHIJKL</RecoverID>
	
	@XmlElement(name = "Memo", required = true)
	private String memo;//<Memo>哈喽</Memo>

	public int getSn() {
		return sn;
	}

	public void setSn(int sn) {
		this.sn = sn;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getRecoverId() {
		return recoverId;
	}

	public void setRecoverId(String recoverId) {
		this.recoverId = recoverId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
