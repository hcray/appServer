package com.krakentouch.server.domain;


/**
 * 终端映射表
 * @author 21829
 *
 */
public class DeskMap {
	private int id;
	private String deskId;	// char(8) comment '串号',
	private int mode;		// int(1) comment '工作模式（0~2分别表示单人独占、多人独占、多人分占）',
	private int status;		// int(1) comment '工作状态（保留）'
	private String startTime; //登录时间
	private String shutdownTime;//退出时间
	private int delFlag;	//int(1) '删除标志（0：没有删除；1：删除）'
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public int getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getShutdownTime() {
		return shutdownTime;
	}
	public void setShutdownTime(String shutdownTime) {
		this.shutdownTime = shutdownTime;
	}
}
