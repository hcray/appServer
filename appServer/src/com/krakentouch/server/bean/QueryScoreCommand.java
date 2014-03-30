package com.krakentouch.server.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="TCP")
@XmlType(name = "QueryScoreCommand", propOrder = { "command", "result", "note", "playerID",
		"score", "money", "prop0" })
public class QueryScoreCommand {
	@XmlElement(name = "Command", required = true)
	private String command; // <Command>login</Command>
	
	@XmlElement(name = "Result", required = true)
	private String result; // <Result>1</Result> ----1 成功 0 失败
	
	@XmlElement(name="Note", required = true)
	private String note; // <Note>....</Note> ----备注
	
	@XmlElement(name="PlayerID", required = true)
	private String playerID; // <PlayerID>ABCDEFGHIJ</PlayerID>
    
	@XmlElement(name="Score", required = true)
	private String score;//<Score>....</Score> ----分数
	
	@XmlElement(name="Money", required = true)
	private String money;//<Money>....</Money> ----金钱
	
	@XmlElement(name="Prop0", required = true)
	private String prop0;//<Prop0>....</Prop0> ----备用1

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPlayerID() {
		return playerID;
	}

	public void setPlayerID(String playerID) {
		this.playerID = playerID;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getProp0() {
		return prop0;
	}

	public void setProp0(String prop0) {
		this.prop0 = prop0;
	}

}
