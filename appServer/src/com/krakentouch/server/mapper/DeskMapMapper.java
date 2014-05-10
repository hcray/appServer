package com.krakentouch.server.mapper;

import com.krakentouch.server.domain.DeskMap;
import com.krakentouch.server.domain.TerminalLog;

public interface DeskMapMapper {
	
	public void insertDeskMap(DeskMap deskMap);
	
	public void deleteDeskMap(DeskMap deskMap);
	
	public void updateDeskMap(DeskMap deskMap);
	
	public void insertTerminalLog(TerminalLog terminalLog);
}
