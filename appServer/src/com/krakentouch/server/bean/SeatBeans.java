package com.krakentouch.server.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Seats")
@XmlType(name = "SeatBeans", propOrder = {"seats" })
public class SeatBeans {
	
	@XmlElement(name="Seat", required = true)
	private List<SeatBean> seats;

	public List<SeatBean> getSeats() {
		return seats;
	}

	public void setSeats(List<SeatBean> seats) {
		this.seats = seats;
	}

	
}
