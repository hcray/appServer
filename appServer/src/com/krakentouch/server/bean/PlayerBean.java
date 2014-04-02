package com.krakentouch.server.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Player")
@XmlType(name = "PlayerBean", propOrder = { "playerID", "gender", "nickName", "icon",
		"grade", "deskID"})
public class PlayerBean {
	@XmlElement(name="PlayerID", required = true)
	private String playerID;// <PlayerID>ABCDEFGHIJ</PlayerID>
	
	@XmlElement(name="Gender", required = true)
	private int gender;//<Gender>0</Gender>
	
	@XmlElement(name="Nickname", required = true)
	private String nickName;//<Nickname>无名</Nickname>
	
	@XmlElement(name="Icon", required = true)
	private int icon;//<Icon>0</Icon>
	
	@XmlElement(name="Grade", required = true)
	private int grade;//<Grade>0</Grade>
	
	@XmlElement(name="DeskID", required = true)
	private String deskID;//<DeskID>00000000</DeskID>

	public String getPlayerID() {
		return playerID;
	}

	public void setPlayerID(String playerID) {
		this.playerID = playerID;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getDeskID() {
		return deskID;
	}

	public void setDeskID(String deskID) {
		this.deskID = deskID;
	}
}
