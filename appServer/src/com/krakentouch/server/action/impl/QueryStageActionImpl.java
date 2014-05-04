package com.krakentouch.server.action.impl;

import java.util.Map;

import org.apache.mina.core.session.IoSession;

import com.krakentouch.server.action.QueryStageAction;
import com.krakentouch.server.bean.QueryStageCommand;
import com.krakentouch.server.bean.StageBean;
import com.krakentouch.server.domain.StageMap;
import com.krakentouch.server.service.GameService;
import com.krakentouch.server.tools.JaxbUtil;
/**
 * 查桌：发出<TCP action="QueryStage" address="OP" category="Game" value="StageSN=2"></TCP>，
 * 服务器端反馈以<TCP action="QueryStageSeat" address="FB" category="Game" value="StageSN=2;Status=0;HostIndex=0;GameID=abcdef"></TCP>
 * @author 21829
 *
 */
public class QueryStageActionImpl implements QueryStageAction {
	
	private GameService gameService;

	@Override
	public String doCommand(IoSession session, Map<String,String> commandMap) {
		String retStr = null;
		//座位号
		String stageSN = commandMap.get("StageSN");
		String command = commandMap.get("action");
		StageMap  stageMap = gameService.queryStageMapByStageSN(stageSN);
		
		QueryStageCommand queryStageCommand = new QueryStageCommand();
		queryStageCommand.setCommand(command);
		queryStageCommand.setResult("1");
		queryStageCommand.setNote("success");
		
		StageBean stageBean = new StageBean();
		stageBean.setGameID(stageMap.getGameID());
		stageBean.setHostIndex(String.valueOf(stageMap.getHostIndex()));
		stageBean.setStageSN(stageSN);
		stageBean.setStatus("2");
		
		queryStageCommand.setStageBean(stageBean );
		
		retStr = JaxbUtil.convertToXml(queryStageCommand, "utf-8");
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
