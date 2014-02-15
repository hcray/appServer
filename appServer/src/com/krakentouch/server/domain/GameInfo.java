package com.krakentouch.server.domain;
/**
 * 游戏数据
 * @author 21829
 *
 */
public class GameInfo {
	private String GameID;		// char(6) primary key comment '串号',
	private String Nickname;	// char(12) comment '昵称',
	private boolean Interdesk;	// boolean comment '是否可以跨终端',
	private int Mode;			// int(1) comment '工作模式（0~5分别表示单人、双人、三人、四人、六人、多人）',
	private boolean Valid;		// boolean comment '当前是否有效'
	
	public String getGameID() {
		return GameID;
	}
	public void setGameID(String gameID) {
		GameID = gameID;
	}
	public String getNickname() {
		return Nickname;
	}
	public void setNickname(String nickname) {
		Nickname = nickname;
	}
	public boolean isInterdesk() {
		return Interdesk;
	}
	public void setInterdesk(boolean interdesk) {
		Interdesk = interdesk;
	}
	public int getMode() {
		return Mode;
	}
	public void setMode(int mode) {
		Mode = mode;
	}
	public boolean isValid() {
		return Valid;
	}
	public void setValid(boolean valid) {
		Valid = valid;
	}
	
}
