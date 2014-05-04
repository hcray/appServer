package com.krakentouch.server.action.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.mina.core.session.IoSession;

import com.krakentouch.server.action.QuerySeatAction;
import com.krakentouch.server.bean.RefreshStageCommand;
import com.krakentouch.server.bean.SeatBean;
import com.krakentouch.server.bean.SeatBeans;
import com.krakentouch.server.domain.SeatMap;
import com.krakentouch.server.service.GameService;
import com.krakentouch.server.tools.JaxbUtil;
/**
 * 查座：然后根据每一个StageSN依次发出
 * <TCP action="QuerySeat" address="OP" category="Game" value="StageSN=2"></TCP>，
 * 服务器端反馈以<TCP action="QuerySeat" address="FB" category="Game" value="StageSN=2;PlayerID=CDEFGHIJKL;SeatIndex=0|StageSN=2;PlayerID=EFGHIJKLMN;SeatIndex=1"></TCP>
 * @author 21829
 *
 */
public class QuerySeatActionImpl implements QuerySeatAction {
	
	private GameService gameService;

	@Override
	public String doCommand(IoSession session, Map<String,String> commandMap) {
		String retStr = null;
		//座位号
		String stageSN = commandMap.get("StageSN");
		List<SeatMap> seatList = gameService.querySeatMapByStageSN(stageSN);
		
		RefreshStageCommand refreshStageCommand = new RefreshStageCommand();
		refreshStageCommand.setCommand(commandMap.get("Command"));
		refreshStageCommand.setResult("1");
		refreshStageCommand.setNote("success");
		SeatBeans stageBeans = new SeatBeans();
		List<SeatBean> seats = new ArrayList<SeatBean>();
		for(SeatMap seatMap : seatList){
			SeatBean stageBean = new SeatBean();
			stageBean.setPlayerID(seatMap.getPlayerID());
			stageBean.setStageSN(seatMap.getStageSN());
			stageBean.setSeatIndex(seatMap.getSeatIndex());
			seats.add(stageBean);
		}
		stageBeans.setSeats(seats);
		refreshStageCommand.setSeatBeans(stageBeans);
		
		retStr = JaxbUtil.convertToXml(refreshStageCommand, "utf-8");
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
