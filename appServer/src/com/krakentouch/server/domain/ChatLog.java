package com.krakentouch.server.domain;
/**
 * 聊天数据
 * @author 21829
 *
 */
public class ChatLog {
	private int SN;			// int(10) primary key comment '流水号',
	private String chatTime;// timestamp comment '时间戳',
	private String SenderID;// char(10) comment '发送者串号',
	private String RecoverID;// char(10) comment '接收者串号',
	private String Memo;	// varchar(256) comment '聊天内容'
	
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
	public String getSenderID() {
		return SenderID;
	}
	public void setSenderID(String senderID) {
		SenderID = senderID;
	}
	public String getRecoverID() {
		return RecoverID;
	}
	public void setRecoverID(String recoverID) {
		RecoverID = recoverID;
	}
	public String getMemo() {
		return Memo;
	}
	public void setMemo(String memo) {
		Memo = memo;
	}
	
}
