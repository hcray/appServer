package com.krakentouch.server.action.impl;

import java.util.Map;

import org.apache.mina.core.session.IoSession;

import com.krakentouch.server.action.JoinStageAction;
import com.krakentouch.server.bean.JoinStageCommand;
import com.krakentouch.server.bean.JoinStageCommandValue;
import com.krakentouch.server.domain.SeatMap;
import com.krakentouch.server.service.GameService;
import com.krakentouch.server.tools.JaxbUtil;
/**
 * 发出
 * 	<TCP>
 * 		<action>JoinStage</action>
 * 		<value>
 * 			<StageSN>2</StageSN>
 * 			<PlayerID>EFGHIJKLMN</PlayerID>
 * 			<SeatIndex>1</SeatIndex>
 * 		</value>
 * 	</TCP>
 * 服务器端在SeatMap表新增一条记录并反馈以
 * <TCP>
 * 		<action>JoinStage</action>
 * 		<value>
 * 			<StageSN>2</StageSN>
 * 			<PlayerID>EFGHIJKLMN</PlayerID>
 * 			<SeatIndex>1</SeatIndex>
 * 		</value>
 * 	</TCP>
 * 并对所有处于本游戏查厅态的用户（通过查询PlayerMap表获知）进行下行通知：
 * <TCP>
 * 		<action>JoinStage</action>
 * 		<value>
 * 			<StageSN>2</StageSN>
 * 			<PlayerID>EFGHIJKLMN</PlayerID>
 * 			<SeatIndex>1</SeatIndex>
 * 		</value>
 * 	</TCP>
 * @author 21829
 *
 */
public class JoinStageActionImpl implements JoinStageAction {
	
	private GameService gameService;

	@Override
	public String doCommand(IoSession session, Map<String,String> commandMap) {
		String retStr = null;
		String command = commandMap.get("action");
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
		
		JoinStageCommandValue joinStageCommandValue = new JoinStageCommandValue();
		joinStageCommandValue.setPlayerID(playerID);
		joinStageCommandValue.setStageSN(stageSN);
		joinStageCommandValue.setSeatIndex(seatIndex);
		
		joinStageCommand.setJoinStageCommandValue(joinStageCommandValue);
		
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
