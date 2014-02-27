package com.krakentouch.server.domain;
/**
 * 游戏数据
 * @author 21829
 *
 */
public class GameInfo {
	private String GameID;		// char(6) primary key comment '串号',
	private String nickName;	// char(12) comment '昵称',
	private boolean interDesk;	// boolean comment '是否可以跨终端',
	private int mode;			// int(1) comment '工作模式（0~5分别表示单人、双人、三人、四人、六人、多人）',
	private boolean valid;		// boolean comment '当前是否有效'
	
	public String getGameID() {
		return GameID;
	}
	public void setGameID(String gameID) {
		GameID = gameID;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public boolean isInterDesk() {
		return interDesk;
	}
	public void setInterDesk(boolean interDesk) {
		this.interDesk = interDesk;
	}
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	
}
