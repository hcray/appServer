package com.krakentouch.server.domain;
/**
 * 玩家积分
 * @author 21829
 *
 */
public class PlayerScore {
	private String PlayerID;	// char(10) primary key comment '串号',
	private int Score;			// int(5) comment '积分',
	private int Money;			// int(5) comment '金钱',
	private int Prop0; 			//int(5) comment 'N种道具的数量'
	
	public String getPlayerID() {
		return PlayerID;
	}
	public void setPlayerID(String playerID) {
		PlayerID = playerID;
	}
	public int getScore() {
		return Score;
	}
	public void setScore(int score) {
		Score = score;
	}
	public int getMoney() {
		return Money;
	}
	public void setMoney(int money) {
		Money = money;
	}
	public int getProp0() {
		return Prop0;
	}
	public void setProp0(int prop0) {
		Prop0 = prop0;
	}
	
	
}
