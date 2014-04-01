package com.krakentouch.server.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.krakentouch.server.bean.EndStageCommand;
import com.krakentouch.server.bean.JoinStageCommand;
import com.krakentouch.server.bean.OpenStageCommand;
import com.krakentouch.server.bean.QueryStageCommand;
import com.krakentouch.server.bean.RefreshStageCommand;
import com.krakentouch.server.bean.SeatBean;
import com.krakentouch.server.bean.SeatBeans;
import com.krakentouch.server.bean.StageBean;
import com.krakentouch.server.bean.StageBeans;
import com.krakentouch.server.bean.StartStageCommand;
import com.krakentouch.server.domain.PlayerMap;
import com.krakentouch.server.domain.SeatMap;
import com.krakentouch.server.domain.StageMap;
import com.krakentouch.server.service.GameService;
import com.krakentouch.server.service.LoginService;
import com.krakentouch.server.tools.JaxbUtil;
import com.krakentouch.server.tools.Utils;


public class MainAction {
	//日志
	private static final Logger LOG = LoggerFactory.getLogger(MainAction.class);
	//登录
	private LoginService loginService;
	
	private GameService gameService;
	
	public String doCommand(String commandStr){
		LOG.debug("doCommand(String commandStr) in... " + commandStr);
		String retStr=null;
		try{
			Map<String, String> commandMap = Utils.parseCommand(commandStr);
			LOG.debug("commandMap: " + commandMap);
			String command = commandMap.get("Command");
			if("login".equals(command)){//登录
				retStr = doLogin(commandMap);
			
			}else if("logout".equals(command)){//注销
				retStr = doLogout(commandMap);
			
			}else if("queryScore".equals(command)){//查分
				retStr = doQueryScore(commandMap);
			
			}else if("queryGames".equals(command)){//查游
				retStr = doQueryGames(commandMap);
			
			}else if("openStage".equals(command)){//开桌
				retStr = doOpenStage(commandMap);
			
			}else if("refreshStage".equals(command)){//刷座
				retStr = doRefreshStage(commandMap);
				
			}else if("queryStage".equals(command)){//查桌
				retStr = doQueryStage(commandMap);
				
			}else if("joinStage".equals(command)){//加入
				retStr = doJoinStage(commandMap);
				
			}else if("startStage".equals(command)){//开玩
				retStr = doStartStage(commandMap);
				
			}else if("checkoutScore".equals(command)){//结分
				retStr = doCheckoutScore(commandMap);
				
			}else if("endStage".equals(command)){//玩闭
				retStr = doEndStage(commandMap);
				
			}else{
				retStr="error,not find command.";
			}
			LOG.debug("doCommand(String commandStr) out... ");
		}catch(Exception e){
			LOG.error(e.getMessage() + e.getCause());
			retStr = "error, messsage:"+e.getMessage() + " ,cause: " + e.getCause();
		}
		return retStr;
	}
	
	/***
	 * 玩家登入
	 * @param commandMap {Command=login, DeskID=00000000, PlayerID=ABCDEFGHIJ}
	 * <TCP>
	 * 	<Command>login</Command>
	 * 	<PlayerID>ABCDEFGHIJ</PlayerID>
	 * 	<DeskID>00000000</DeskID>
	 * </TCP>
	 * 
	 *  @return
	 *  <TCP>
	 *   <Command>login</Command>
	 *   <Result>1</Result> ----1 成功 0 失败
	 *   <Note>....</Note> ----备注
	 *   <PlayerID>ABCDEFGHIJ</PlayerID>
	 *   <DeskID>00000000</DeskID>
	 *   <Status>0</Status>
	 *   <GameID></GameID>
	 *	</TCP>
	 * @throws Exception 
	 * 
	 */
	public String doLogin(Map<String,String> commandMap) throws Exception{
		String retStr = loginService.login(commandMap);
		return retStr;
	}
	
	/**
	 * 注销
	 * @param commandMap
	 * @return
	 * @throws Exception 
	 */
	public String doLogout(Map<String,String> commandMap) throws Exception{
		String retStr = loginService.logout(commandMap);
		return retStr;
		
	}
	
	/**
	 * 查分
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	public String doQueryScore(Map<String,String> commandMap) throws Exception{
		String retStr = loginService.queryScore(commandMap);
		return retStr;
	}
	
	/**
	 * 查游
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	public String doQueryGames(Map<String,String> commandMap) throws Exception{
		String retStr = gameService.queryGames(commandMap);
		return retStr;
	}
	
	
	/**
	 * 开桌
	 * @param commandMap
	 * @return
	 */
	public String doOpenStage(Map<String,String> commandMap){
		String retStr = null;
		String gameID = commandMap.get("GameID");
		String hostIndex = commandMap.get("HostIndex");
		String playerID = commandMap.get("PlayerID");
		
		//加入桌子
		StageMap stageMap = new StageMap();
		stageMap.setGameID(gameID);
		stageMap.setHostIndex(Integer.parseInt(hostIndex));
		stageMap.setStatus(0);//0:待机 1：游戏
		gameService.insertStageMap(stageMap);
		
		//加入位子
		SeatMap seatMap = new SeatMap();
		seatMap.setPlayerID(playerID);
		seatMap.setSeatIndex(0);//座位序号 为 0 开桌的默认的设置为第一个座位 0
		int stageSN = stageMap.getStageSN();
		seatMap.setStageSN(stageSN);
		gameService.insertSeatMap(seatMap);

		//更新玩家状态
		PlayerMap playerMap = new PlayerMap();
		playerMap.setPlayerID(playerID);
		playerMap.setStatus(3); //游戏状态 ???? 在位子上，没有开始游戏
		loginService.updatePlayerStatus(playerMap);
		
		//返回的字符串
		OpenStageCommand openStageCommand = new OpenStageCommand();
		openStageCommand.setCommand(commandMap.get("Command"));
		openStageCommand.setResult("1");
		openStageCommand.setNote("success");
		openStageCommand.setStageSN(String.valueOf(stageSN));
		openStageCommand.setHostIndex(Integer.parseInt(hostIndex));
		openStageCommand.setSeatIndex(0);
		openStageCommand.setStatus(3);
		
		retStr = JaxbUtil.convertToXml(openStageCommand, "utf-8");
		return retStr;
	}
	
	/**
	 * 刷座
	 * @param commandMap
	 * @return
	 */
	public String doRefreshStage(Map<String,String> commandMap){
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
		return retStr;
	}
	
	/**
	 * 查座
	 * @param commandMap
	 * @return
	 */
	public String doQueryStage(Map<String,String> commandMap){
		String retStr = null;
		//命令
		String command = commandMap.get("Command");
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
		return retStr;
	}
	
	/**
	 * 加入
	 * @param commandMap
	 * <TCP>
	 *      <Command>joinStage</Command>
	 *      <StageSN>abcdef</StageSN>
	 *      <PlayerID>CDEFGHIJKL</PlayerID>
	 *      <SeatIndex>CDEFGHIJKL</SeatIndex>
	 * </TCP>
	 *
	 * @return
	 */
	public String doJoinStage(Map<String,String> commandMap){
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
		return retStr;
	}
	/**
	 * 开玩
	 * @param commandMap
	 * <TCP>
	 * 	<Command>startStage</Command>
	 * 	<StageSN>1</StageSN>
	 * 	<Status>1</Status>
	 * 	<PlayerID>CDEFGHIJKL</PlayerID>
	 * </TCP>
	 *
	 * @return
	 */
	public String doStartStage(Map<String,String> commandMap){
		String retStr = null;
		String command = commandMap.get("Command");
		String stageSN = commandMap.get("StageSN");
		String status = commandMap.get("Status");
		String playerID = commandMap.get("PlayerID");
		
		//更新座位状态
		StageMap stageMap = new StageMap();
		stageMap.setStageSN(Integer.parseInt(stageSN));
		stageMap.setStatus(Integer.parseInt(status));
		gameService.updateStageMap(stageMap);
		
		//更新玩家状态
		PlayerMap playerMap = new PlayerMap();
		playerMap.setPlayerID(playerID);
		playerMap.setStatus(3);//玩家游戏状态
		loginService.updatePlayerStatus(playerMap);
		
		StartStageCommand startStageCommand = new StartStageCommand();
		startStageCommand.setCommand(command);
		startStageCommand.setResult("1");
		startStageCommand.setNote("success");
		startStageCommand.setStageSN(stageSN);
		startStageCommand.setStatus(status);
		startStageCommand.setPlayerID(playerID);

		retStr = JaxbUtil.convertToXml(startStageCommand, "utf-8");
		return retStr;
	}
	
	/**
	 * 结分
	 * @param commandMap
	 * @return
	 */
	public String doCheckoutScore(Map<String,String> commandMap){
		String retStr = null;
		
		
		return retStr;
	}
	
	
	/**
	 * 玩闭
	 * @param commandMap
	 * 	<TCP>
     *		<Command>endStage</Command>
     *		<StageSN>abcdef</StageSN>
     *		<PlayerID>CDEFGHIJKL</PlayerID>
	 * 	</TCP>
     *
	 * @return
	 */
	public String doEndStage(Map<String,String> commandMap){
		String retStr = null;
		//删除座位
		String command = commandMap.get("Command");
		String stageSN = commandMap.get("StageSN");
		String playerID = commandMap.get("PlayerID");
		
		
		SeatMap seatMap = new SeatMap();
		seatMap.setStageSN(Integer.parseInt(stageSN));
		gameService.deleteSeatMap(seatMap);
		//查询当前桌的数据
		StageMap retStageMap = gameService.queryStageMapByStageSN(stageSN);
		
		StageMap stageMap = new StageMap();
		stageMap.setStageSN(Integer.parseInt(stageSN));
		gameService.deleteStageMap(stageMap);
		
		//更新同桌的玩家状态
		List<SeatMap> seatList = gameService.querySeatMapByStageSN(stageSN);
		for(SeatMap sm : seatList){
			String pId = sm.getPlayerID();
			PlayerMap playerMap = new PlayerMap();
			playerMap.setPlayerID(pId);
			playerMap.setStatus(2);//玩家查桌状态
			loginService.updatePlayerStatus(playerMap);
		}
		
		EndStageCommand endStageCommand = new EndStageCommand();
		endStageCommand.setCommand(command);
		endStageCommand.setResult("1");
		endStageCommand.setNote("success");
		endStageCommand.setGameID(retStageMap.getGameID());
		endStageCommand.setHostIndex(retStageMap.getHostIndex());
		endStageCommand.setPlayerID(playerID);
		endStageCommand.setStageSN(retStageMap.getStageSN());
		endStageCommand.setStatus(String.valueOf(retStageMap.getStatus()));
		
		retStr = JaxbUtil.convertToXml(endStageCommand, "utf-8");
		return retStr;
	}
	
	
	
	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public GameService getGameService() {
		return gameService;
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}
	
}
