package com.krakentouch.server.domain;
/**
 * 物理房间数据
 * @author 21829
 *
 */
public class RoomInfo {
	private String RoomID; 		//char(6) primary key comment '串号',
	private String Nickname; 	//char(12) comment '昵称',
	private int Grade;			// int(5) comment '等级',
	private int Mode;			// int(1) comment '工作模式（0~1分别表示单桌、多桌）',
	private int Status;			// int(1) comment '工作状态（保留）'
	
	public String getRoomID() {
		return RoomID;
	}
	public void setRoomID(String roomID) {
		RoomID = roomID;
	}
	public String getNickname() {
		return Nickname;
	}
	public void setNickname(String nickname) {
		Nickname = nickname;
	}
	public int getGrade() {
		return Grade;
	}
	public void setGrade(int grade) {
		Grade = grade;
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
