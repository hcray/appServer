package com.krakentouch.server.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Game")
@XmlType(name = "GameInfoBeans", propOrder = {"gameInfo" })
public class GameInfoBeans {
	
	@XmlElement(name="Game", required = true)
	private List<GameInfoBean> gameInfo; // <Gameinfo>... </Gameinfo>

	public List<GameInfoBean> getGameInfo() {
		return gameInfo;
	}

	public void setGameInfo(List<GameInfoBean> gameInfo) {
		this.gameInfo = gameInfo;
	}
}
