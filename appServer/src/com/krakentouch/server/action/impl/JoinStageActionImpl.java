package com.krakentouch.server.action.impl;

import java.util.Map;

import org.apache.mina.core.session.IoSession;

import com.krakentouch.server.action.JoinStageAction;
import com.krakentouch.server.bean.JoinStageCommand;
import com.krakentouch.server.bean.QueryStageCommand;
import com.krakentouch.server.bean.StageBean;
import com.krakentouch.server.domain.SeatMap;
import com.krakentouch.server.domain.StageMap;
import com.krakentouch.server.service.GameService;
import com.krakentouch.server.tools.JaxbUtil;
/**
 * 查桌：发出<TCP action="QueryStage" address="OP" category="Game" value="StageSN=2"></TCP>，
 * 服务器端反馈以<TCP action="QueryStageSeat" address="FB" category="Game" value="StageSN=2;Status=0;HostIndex=0;GameID=abcdef"></TCP>
 * @author 21829
 *
 */
public class JoinStageActionImpl implements JoinStageAction {
	
	private GameService gameService;

	@Override
	public String doCommand(IoSession session, Map<String,String> commandMap) {
		String retStr = null;
		String command = commandMap.get("Command");
		String stageSN = commandMap.get("StageSN");
		String playerID = commandMap.get("PlayerID");
		String seatIndex = commandMap.get("SeatIndex");
		
		SeatMap seatMap = new SeatMap();
		seatMap.setPlayerID(playerID);
		seatMap .setSeatIndex(Integer.parseInt(seatIndex));
		seatMap .setStageSN(Integer.parseInt(stageSN));
		gameService.insertSeatMap(seatMap);
		
		JoinStageCommand joinStageCommand = new JoinStageCommand();
		joinStageCommand.setCommand(command);
		joinStageCommand.setResult("1");
		joinStageCommand.setNote("success");
		joinStageCommand.setPlayerID(playerID);
		joinStageCommand.setStageSN(stageSN);
		joinStageCommand.setSeatIndex(seatIndex);
		
		retStr = JaxbUtil.convertToXml(joinStageCommand, "utf-8");
		session.write(retStr);
		return retStr;
	}

	public GameService getGameService() {
		return gameService;
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}
}
