package com.krakentouch.server.domain;
/**
 * 物理终端数据
 * @author 21829
 *
 */
public class DeskInfo {
	private String DeskID;	// char(8) primary key comment '串号',
	private String Nickname;// char(12)comment '昵称',
	private String RoomID;	// char(6)comment '串号'
	
	public String getDeskID() {
		return DeskID;
	}
	public void setDeskID(String deskID) {
		DeskID = deskID;
	}
	public String getNickname() {
		return Nickname;
	}
	public void setNickname(String nickname) {
		Nickname = nickname;
	}
	public String getRoomID() {
		return RoomID;
	}
	public void setRoomID(String roomID) {
		RoomID = roomID;
	}
	
}
