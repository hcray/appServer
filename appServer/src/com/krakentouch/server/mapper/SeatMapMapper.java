package com.krakentouch.server.mapper;

import java.util.List;

import com.krakentouch.server.domain.SeatMap;

public interface SeatMapMapper {
	
	public void insertSeatMap(SeatMap seatMap);
	
	public List<SeatMap> querySeatMapByStageSN(String StageSN);
	
	public void deleteSeatMap(SeatMap seatMap);
}
