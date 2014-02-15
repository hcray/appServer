package com.krakentouch.server.domain;
/**
 * 数据库操作日志
 * @author 21829
 *
 */
public class DBLog {
	private int SN;			// int(10) primary key comment '流水号',
	private String chatTime;// timestamp comment '时间戳',
	private String User;	// char(20) comment '操作者账号',
	private String Script;	// varchar(256) comment '数据库操作描述'
	
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
	public String getUser() {
		return User;
	}
	public void setUser(String user) {
		User = user;
	}
	public String getScript() {
		return Script;
	}
	public void setScript(String script) {
		Script = script;
	}
	
}
