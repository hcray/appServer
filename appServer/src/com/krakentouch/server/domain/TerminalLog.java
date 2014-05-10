package com.krakentouch.server.domain;
/**
 * 终端日志
 * @author 21829
 *
 */
public class TerminalLog {
	private int sn;			// int(10) primary key comment '流水号',
	private String time;// timestamp comment '时间戳',
	private String deskId;	// char(8) comment '终端串号',
	private String script;// varchar(1024)comment '终端日志内容'
	
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
}
