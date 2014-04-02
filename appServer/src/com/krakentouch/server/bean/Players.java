package com.krakentouch.server.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Players")
@XmlType(name = "Players", propOrder = { "players"})
public class Players {
	@XmlElement(name="Players", required = true)
	private List<PlayerBean> players;

	public List<PlayerBean> getPlayers() {
		return players;
	}

	public void setPlayers(List<PlayerBean> players) {
		this.players = players;
	}
	
}
