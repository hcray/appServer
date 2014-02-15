package com.krakentouch.server.domain;
/**
 * 终端映射表
 * @author 21829
 *
 */
public class DeskMap {
	private String DeskID;	// char(8) comment '串号',
	private int Mode;		// int(1) comment '工作模式（0~2分别表示单人独占、多人独占、多人分占）',
	private int Status;		// int(1) comment '工作状态（保留）'
	
	public String getDeskID() {
		return DeskID;
	}
	public void setDeskID(String deskID) {
		DeskID = deskID;
	}
	public int getMode() {
		return Mode;
	}
	public void setMode(int mode) {
		Mode = mode;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
}
