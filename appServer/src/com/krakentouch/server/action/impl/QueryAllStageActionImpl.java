package com.krakentouch.server.action.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.mina.core.session.IoSession;

import com.krakentouch.server.action.QueryAllStageAction;
import com.krakentouch.server.bean.QueryStageCommand;
import com.krakentouch.server.bean.StageBean;
import com.krakentouch.server.bean.StageBeans;
import com.krakentouch.server.domain.PlayerMap;
import com.krakentouch.server.domain.StageMap;
import com.krakentouch.server.service.GameService;
import com.krakentouch.server.service.LoginService;
import com.krakentouch.server.tools.JaxbUtil;
/**
 * 查厅：首先发出
 * 	<TCP action="QueryAllStage" address="OP" category="Game" value="GameID=abcdef;PlayerID=CDEFGHIJKL"></TCP>，
 * 	服务器端对该用户更新PlayerMap表的Status字段
 * 	并反馈以<TCP action="QueryAllStage" address="FB" category="Game" value="StageSN=1;Status=0;HostIndex=2;GameID=abcdef|StageSN=2;Status=0;HostIndex=0;GameID=abcdef"></TCP>，
 * 	并对该用户以及所有处于询众态的用户（通过查询PlayerMap获知）进行下行通知：
 * 	<TCP action="QueryAllStage" address="NT" category="Account" value="PlayerID=CDEFGHIJKL;Status=2"></TCP>
 * @author 21829
 *
 */
public class QueryAllStageActionImpl implements QueryAllStageAction {
	
	private LoginService loginService;
	
	private GameService gameService;

	@Override
	public String doCommand(IoSession session, Map<String,String> commandMap) {
		String retStr = null;
		String command = commandMap.get("action");
		String gameID = commandMap.get("GameID");
		String playerID = commandMap.get("PlayerID");
		
		//更新玩家状态
		PlayerMap playerMap = new PlayerMap();
		playerMap.setPlayerID(playerID);
		playerMap.setStatus(2);//工作状态（0~3分别表示待机、询人、查桌、游戏）
		loginService.updatePlayerStatus(playerMap);
		
		//查座
		List<StageMap> stageList = gameService.queryStageMapByGameId(gameID);
		List<StageBean> stageBeanList = new ArrayList<StageBean>();
		QueryStageCommand queryStageCommand = new QueryStageCommand();
		queryStageCommand.setCommand(command);
		queryStageCommand.setResult("1");
		queryStageCommand.setNote("success");
		for(StageMap stageMap : stageList){
			StageBean stageBean = new StageBean();
			stageBean.setGameID(stageMap.getGameID());
			stageBean.setHostIndex(String.valueOf(stageMap.getHostIndex()));
			stageBean.setStageSN(String.valueOf(stageMap.getStageSN()));
			stageBean.setStatus(String.valueOf(stageMap.getStatus()));
			
			stageBeanList.add(stageBean);
		}
		
		StageBeans stageBeans = new StageBeans();
		stageBeans.setStageBeans(stageBeanList);

		queryStageCommand.setStageBeans(stageBeans);
		
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

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
}
