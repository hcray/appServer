package com.krakentouch.server.domain;
/**
 * 终端日志
 * @author 21829
 *
 */
public class TerminalLog {
	private int SN;			// int(10) primary key comment '流水号',
	private String chatTime;// timestamp comment '时间戳',
	private String DeskID;	// char(8) comment '终端串号',
	private String Script;// varchar(1024)comment '终端日志内容'
	
	public int getSN() {
		return SN;
	}
	public void setSN(int sN) {
		SN = sN;
	}
	public String getChatTime() {
		return chatTime;
	}
	public void setChatTime(String chatTime) {
		this.chatTime = chatTime;
	}
	public String getDeskID() {
		return DeskID;
	}
	public void setDeskID(String deskID) {
		DeskID = deskID;
	}
	public String getScript() {
		return Script;
	}
	public void setScript(String script) {
		Script = script;
	}
}
