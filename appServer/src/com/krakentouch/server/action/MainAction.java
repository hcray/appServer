package com.krakentouch.server.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.krakentouch.server.bean.OpenStageCommand;
import com.krakentouch.server.bean.OpenStageCommandValue;
import com.krakentouch.server.bean.PlayerBean;
import com.krakentouch.server.bean.Players;
import com.krakentouch.server.bean.QueryAllStageCommand;
import com.krakentouch.server.bean.RefreshStageCommand;
import com.krakentouch.server.bean.SearchPlayerCommand;
import com.krakentouch.server.bean.SeatBean;
import com.krakentouch.server.bean.SeatBeans;
import com.krakentouch.server.bean.StageBean;
import com.krakentouch.server.bean.StageBeans;
import com.krakentouch.server.bean.StartupBean;
import com.krakentouch.server.bean.StartupBeanValue;
import com.krakentouch.server.domain.DeskMap;
import com.krakentouch.server.domain.Player;
import com.krakentouch.server.domain.PlayerMap;
import com.krakentouch.server.domain.SeatMap;
import com.krakentouch.server.domain.StageMap;
import com.krakentouch.server.service.ChatService;
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
	
	private ChatService chatService;
	
	/**
	 * 物理端登录
	 */
	private TerminalLoginAction terminalLoginAction;

	/**
	 * 物理端登出 
	 */
	private TerminalLogoutAction terminalLogoutAction;
	
	/***
	 * 物理端登记 
	 */
	private LoginWriteAction loginWriteAction;
	
	/**
	 * 用户登录
	 */
	private PlayerLoginAction playerLoginAction;
	
	/**
	 * 用户登出
	 */
	private PlayerLogoutAction playerLogoutAction;
	
	/**
	 * 查分
	 */
	private QueryScoreAction queryScoreAction;
	
	/**
	 * 查游
	 */
	private QueryGameAction queryGameAction;
	
	/**
	 * 开桌
	 */
	private NewStageAction newStageAction;
	
	/**
	 * 查厅
	 */
	private QueryAllStageAction queryAllStageAction;

	/**
	 * 查座
	 */
	private QuerySeatAction querySeatAction;
	
	/***
	 * 查桌
	 */
	private QueryStageAction queryStageAction;

	/**
	 * 参桌
	 */
	private JoinStageAction joinStageAction;
	
	/**
	 * 开始游戏
	 */
	private BeginGameAction beginGameAction;
	
	/**
	 * 结束游戏
	 */
	private ConcludeGameAction concludeGameAction;
	
	/**
	 * 算分
	 */
	private ComputeScoreAction computeScoreAction;
	
	/**
	 * 收桌
	 */
	private CloseStageAction closeStageAction;
	
	/**
	 * 询众
	 */
	private QueryAllPlayerAction queryAllPlayerAction; 
	
	/**
	 * 询人
	 */
	private QueryPlayerAction queryPlayerAction; 
	
	/**
	 * 聊天
	 */
	private ChatAction chatAction;
	
	/**
	 * 赠送
	 */
	private GiveGiftAction giveGiftAction;
	
	public Map<String, String> doCommand(IoSession session, String commandStr){
		LOG.debug("doCommand(String commandStr) in... " + commandStr);
		Map<String, String> retMap = new HashMap<String, String>();
		String retStr=null;
		try{
			Map<String, String> commandMap = Utils.parseCommand(commandStr);
			LOG.debug("commandMap: " + commandMap);
			String command = commandMap.get("action");
			
			if("TerminalLogin".equals(command)){//物理端登陆
				terminalLoginAction.doCommand(session, commandMap);
				//retStr = doStartup(commandMap);
				
			}else if("TerminalLogout".equals(command)){//物理端签出
				terminalLogoutAction.doCommand(session, commandMap);
				
			}else if("LoginWrite".equals(command)){//物理终端登记
				loginWriteAction.doCommand(session, commandMap);
				
			}else if("PlayerLogin".equals(command)){//登录
				
				playerLoginAction.doCommand(session, commandMap);
				/*
				retStr = doLogin(commandMap);
				String playerID = commandMap.get("PlayerID");
				retMap.put("playerID", playerID);
				*/
			}else if("PlayerLogout".equals(command)){//注销
				//retStr = doLogout(commandMap);
				playerLogoutAction.doCommand(session, commandMap);
				
			}else if("QueryScore".equals(command)){//查分
				//retStr = doQueryScore(commandMap);
				queryScoreAction.doCommand(session, commandMap);
				
			}else if("QueryGame".equals(command)){//查游
				//retStr = doQueryGames(commandMap);
				queryGameAction.doCommand(session, commandMap);
			
			}else if("NewStage".equals(command)){//开桌
				//retStr = doOpenStage(commandMap);
				newStageAction.doCommand(session, commandMap);
				
			}else if("QueryAllStage".equals(command)){//查厅
				queryAllStageAction.doCommand(session, commandMap);
				
			}else if("QuerySeat".equals(command)){//查座
				//retStr = doRefreshStage(commandMap);
				querySeatAction.doCommand(session, commandMap);
				
			}else if("QueryStage".equals(command)){//查桌
				//retStr = doQueryStage(commandMap);
				queryStageAction.doCommand(session, commandMap);
				
			}else if("JoinStage".equals(command)){//参桌
				//retStr = doJoinStage(commandMap);
				joinStageAction.doCommand(session, commandMap);
				
			}else if("BeginGame".equals(command)){//开玩
				//retStr = doStartStage(commandMap);
				beginGameAction.doCommand(session, commandMap);
				
			}else if("ConcludeGame".equals(command)){//结分
				//retStr = doCheckoutScore(commandMap);
				concludeGameAction.doCommand(session, commandMap);
				
			}else if("ComputeScore".equals(command)){//算分
				//retStr = doCheckoutScore(commandMap);
				computeScoreAction.doCommand(session, commandMap);
				
			}else if("CloseStage".equals(command)){//玩闭
				//retStr = doEndStage(commandMap);
				closeStageAction.doCommand(session, commandMap);
				
			}else if("QueryAllPlayer".equals(command)){//询众
				//retStr = doSearchPlayer(commandMap);
				queryAllPlayerAction.doCommand(session, commandMap);
				
			}else if("QueryPlayer".equals(command)){//询人
				//retStr = doSearchPlayer(commandMap);
				queryPlayerAction.doCommand(session, commandMap);
				
			}else if("Chat".equals(command)){//聊天
				/*String[] ret = doSendMessage(commandMap); 
				retStr = ret[0];
				String receiveMessage = ret[1];
				retMap.put("receiveMessage", receiveMessage);
				
				String receiveId = commandMap.get("RecoverID");//接收者
				retMap.put("receiveId", receiveId);*/
				chatAction.doCommand(session, commandMap);
				
			}else if("GiveGift".equals(command)){
				//String[] ret = doPresent(commandMap);
				giveGiftAction.doCommand(session, commandMap);
				
			}else{
				retStr="error,not find command.";
				session.write(retStr);
			}
			
			//retMap.put("command",command);
			//retMap.put("result",retStr);
			LOG.debug("doCommand(String commandStr) out... ");
		}catch(Exception e){
			LOG.error(e.getMessage() + e.getCause());
			retStr = "error, messsage:"+e.getMessage() + " ,cause: " + e.getCause();
			session.write(retStr);
		}
		return retMap;
	}
	
	
	//开机
	public String doStartup(Map<String,String> commandMap) throws Exception{
		String retStr = null;
		String command = commandMap.get("action");
		//String value = commandMap.get("value");
		
		//Map<String,String> valueMap = Utils.parseCommand(value);
		String deskId = commandMap.get("DeskID");
		String address = commandMap.get("address");
		String category = commandMap.get("category");;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String startTime = sdf.format(new Date());
		DeskMap deskMap = new DeskMap();
		deskMap.setDeskId(deskId);
		deskMap.setStartTime(startTime);
		deskMap.setDelFlag(0); //删除标志（0：没有删除；1：删除）
		deskMap.setMode(0); //工作模式（0~2分别表示单人独占、多人独占、多人分占）
		deskMap.setStatus(0);//工作状态（保留）
		loginService.insertDeskMap(deskMap);
		
		StartupBean startupBean = new StartupBean();
		StartupBeanValue value = new StartupBeanValue();
		startupBean.setCommand(command);
		startupBean.setResult("1");
		startupBean.setNote("success");
		value.setDeskId(deskId);
		value.setMode(0);
		value.setStatus(0);
		startupBean.setValue(value);
		startupBean.setAddress(address);
		startupBean.setCategory(category);
		
		retStr = JaxbUtil.convertToXml(startupBean, "utf-8");
		return retStr;
	}
	
	//物理端登出
	public String doShutdown(Map<String,String> commandMap) throws Exception{
		String retStr = null;
		String command = commandMap.get("action");
		String deskId = commandMap.get("DeskID");
		String address = commandMap.get("address");
		String category = commandMap.get("category");;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String shutdownTime = sdf.format(new Date());
		DeskMap deskMap = new DeskMap();
		deskMap.setDeskId(deskId);
		deskMap.setShutdownTime(shutdownTime);
		deskMap.setDelFlag(1); //删除标志（0：没有删除；1：删除）
		deskMap.setStatus(0);//工作状态（保留）
		loginService.updateDeskMap(deskMap);
		
		StartupBean startupBean = new StartupBean();
		StartupBeanValue value = new StartupBeanValue();
		startupBean.setCommand(command);
		startupBean.setResult("1");
		startupBean.setNote("success");
		value.setDeskId(deskId);
		value.setMode(0);
		value.setStatus(0);
		startupBean.setValue(value);
		startupBean.setAddress(address);
		startupBean.setCategory(category);
		
		retStr = JaxbUtil.convertToXml(startupBean, "utf-8");
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
	/*public String doLogin(Map<String,String> commandMap) throws Exception{
		String retStr = loginService.login(commandMap);
		return retStr;
	}
	*/
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
		OpenStageCommandValue openStageCommandValue = new OpenStageCommandValue(); 
		
		openStageCommand.setCommand(commandMap.get("Command"));
		openStageCommand.setResult("1");
		openStageCommand.setNote("success");
		
		openStageCommandValue.setStageSN(String.valueOf(stageSN));
		openStageCommandValue.setHostIndex(Integer.parseInt(hostIndex));
		openStageCommandValue.setSeatIndex(0);
		openStageCommandValue.setStatus(3);
		
		openStageCommand.setOpenStageCommandValue(openStageCommandValue);
		
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
		QueryAllStageCommand queryAllStageCommand = new QueryAllStageCommand();
		queryAllStageCommand.setCommand(command);
		queryAllStageCommand.setResult("1");
		queryAllStageCommand.setNote("success");
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

		queryAllStageCommand.setStageBeans(stageBeans);
		
		retStr = JaxbUtil.convertToXml(queryAllStageCommand, "utf-8");
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
	/*public String doJoinStage(Map<String,String> commandMap){
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
	}*/
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
	/*
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
	*/
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
/*	public String doEndStage(Map<String,String> commandMap){
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
	}*/
	
	/***
	 * 询人
	 * @param commandMap
	 * @return
	 */
	public String doSearchPlayer(Map<String,String> commandMap){
		String retStr = null;
		String command = commandMap.get("Command");
		
		List<Player> playerlist = loginService.queryAllOnlinePlayer();
		List<PlayerBean> playerBeans = new ArrayList<PlayerBean>();
		for(Player p : playerlist){
			PlayerBean playerBean = new PlayerBean();
			playerBean.setPlayerID(p.getPlayerID());
			playerBean.setDeskID(p.getDeskID());
			playerBean.setGender(p.getGender());
			playerBean.setGrade(p.getGrade());
			playerBean.setIcon(p.getIcon());
			playerBean.setNickName(p.getNickName());
			playerBeans.add(playerBean);
		}
		
		Players players =  new Players();
		players.setPlayers(playerBeans);
		
		SearchPlayerCommand searchPlayerCommand = new SearchPlayerCommand();
		searchPlayerCommand.setCommand(command);
		searchPlayerCommand.setResult("1");
		searchPlayerCommand.setNote("success");
		searchPlayerCommand.setPlayers(players);
		
		retStr = JaxbUtil.convertToXml(searchPlayerCommand, "utf-8");
		return retStr;
	}
	
	/**
	 * 聊天
	 * @param commandMap
	 * @return
	 * 推送给接收方
	 * <TCP>
	 * 		<Command>receiveMessage</Command>
	 * 		<SN>0</SN>
	 * 		<Time>xxxxxxxx</Time>
	 * 		<SenderID>ABCDEFGHIJ</SenderID>
	 * 		<RecverID>CDEFGHIJKL</RecverID>
	 * 		<Memo>哈喽</Memo> 
	 * </TCP>
	 *
	 */
/*	public String[] doSendMessage(Map<String,String> commandMap){
		String retStr = null;
		String command = commandMap.get("Command"); //命令
		String senderID = commandMap.get("SenderID");//发送者
		String recoverID = commandMap.get("RecoverID");//接收者
		String memo = commandMap.get("Memo");//发送内容
		//chatTime
		
		ChatLog chatLog = new ChatLog();
		chatLog.setRecoverID(recoverID);
		chatLog.setSenderID(senderID);
		chatLog.setMemo(memo);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String chatTime = sdf.format(new Date());
		chatLog.setChatTime(chatTime );
		chatService.insertChatLog(chatLog );
		
		CommandBean commandBean = new CommandBean();
		commandBean.setCommand(command);
		commandBean.setResult("1");
		commandBean.setNote("success");
		retStr = JaxbUtil.convertToXml(commandBean, "utf-8");
		
		ChatMessageBean receiveMessageBean = new ChatMessageBean();
		receiveMessageBean.setCommand("receiveMessage");
		receiveMessageBean.setSn(chatLog.getSN());
		receiveMessageBean.setTime(chatTime);
		receiveMessageBean.setRecoverId(recoverID);
		receiveMessageBean.setSenderId(senderID);
		receiveMessageBean.setMemo(memo);
		
		String receiveMessage = JaxbUtil.convertToXml(receiveMessageBean, "utf-8");
		
		return new String[]{retStr,receiveMessage};
	}*/
	
	/*	
	public String[] doPresent(Map<String,String> commandMap) throws Exception{
		//String command = commandMap.get("Command"); //命令
		String senderID = commandMap.get("SenderID");//发送者
		String recoverID = commandMap.get("RecoverID");//接收者
		int score = Integer.parseInt(commandMap.get("Score"));//分数
		int money = Integer.parseInt(commandMap.get("Money"));//金钱
		int prop0 = Integer.parseInt(commandMap.get("Prop0"));//道具
		
		PlayerScore playerScore = loginService.queryScoreBean(senderID);
		PlayerScore recoverScore = loginService.queryScoreBean(recoverID);
		if(playerScore.getScore() < score || playerScore.getMoney() < money || playerScore.getProp0() < prop0){
			return new String[]{"score or money or prop0 is not enough"};
		}else{
			//发送者减少
			playerScore.setScore(playerScore.getScore() - score);
			playerScore.setMoney(playerScore.getMoney() - money);
			playerScore.setProp0(playerScore.getProp0() - prop0);
			loginService.updatePlayerScore(playerScore);
			
			ChatLog chatLog = new ChatLog();
			chatLog.setSenderID("0000000000"); //系统发送
			chatLog.setRecoverID(senderID);
			StringBuffer memoBuffer = new StringBuffer();
			memoBuffer.append("您赠送 ").append(recoverID);
			if(score != 0) memoBuffer.append(" score:").append(score);
			if(money != 0) memoBuffer.append(" money:").append(money);
			if(prop0 != 0) memoBuffer.append(" prop0:").append(prop0);
			chatLog.setMemo(memoBuffer.toString());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String chatTime = sdf.format(new Date());
			chatLog.setChatTime(chatTime);
			chatService.insertChatLog(chatLog );
			
			ChatMessageBean senderPresentBean = new ChatMessageBean();
			senderPresentBean.setCommand("sendPresent");
			senderPresentBean.setSn(chatLog.getSN());
			senderPresentBean.setTime(chatLog.getChatTime());
			senderPresentBean.setRecoverId(chatLog.getRecoverID());
			senderPresentBean.setSenderId(chatLog.getSenderID());
			senderPresentBean.setMemo(chatLog.getMemo());
			String senderPresent = JaxbUtil.convertToXml(senderPresentBean, "utf-8");
			
			//接受者增加
			recoverScore.setScore(recoverScore.getScore() + score);
			recoverScore.setMoney(recoverScore.getMoney() + money);
			recoverScore.setProp0(recoverScore.getProp0() + prop0);
			loginService.updatePlayerScore(recoverScore);
			chatLog.setRecoverID(recoverID);
			StringBuffer rMemoBuffer = new StringBuffer();
			rMemoBuffer.append(senderID).append("赠送您 ");
			if(score != 0) rMemoBuffer.append(" score:").append(score);
			if(money != 0) rMemoBuffer.append(" money:").append(money);
			if(prop0 != 0) rMemoBuffer.append(" prop0:").append(prop0);
			chatLog.setMemo(rMemoBuffer.toString());
			chatService.insertChatLog(chatLog);
			
			ChatMessageBean receivePresentBean = new ChatMessageBean();
			receivePresentBean.setCommand("receivePresent");
			receivePresentBean.setSn(chatLog.getSN());
			receivePresentBean.setTime(chatLog.getChatTime());
			receivePresentBean.setRecoverId(chatLog.getRecoverID());
			receivePresentBean.setSenderId(chatLog.getSenderID());
			receivePresentBean.setMemo(chatLog.getMemo());
			String receivePresent = JaxbUtil.convertToXml(receivePresentBean, "utf-8");
			return new String[]{senderPresent, receivePresent};
		}
		
	}*/
	
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

	public ChatService getChatService() {
		return chatService;
	}

	public void setChatService(ChatService chatService) {
		this.chatService = chatService;
	}


	public TerminalLoginAction getTerminalLoginAction() {
		return terminalLoginAction;
	}


	public void setTerminalLoginAction(TerminalLoginAction terminalLoginAction) {
		this.terminalLoginAction = terminalLoginAction;
	}


	public TerminalLogoutAction getTerminalLogoutAction() {
		return terminalLogoutAction;
	}


	public void setTerminalLogoutAction(TerminalLogoutAction terminalLogoutAction) {
		this.terminalLogoutAction = terminalLogoutAction;
	}


	public PlayerLoginAction getPlayerLoginAction() {
		return playerLoginAction;
	}


	public void setPlayerLoginAction(PlayerLoginAction playerLoginAction) {
		this.playerLoginAction = playerLoginAction;
	}


	public PlayerLogoutAction getPlayerLogoutAction() {
		return playerLogoutAction;
	}


	public void setPlayerLogoutAction(PlayerLogoutAction playerLogoutAction) {
		this.playerLogoutAction = playerLogoutAction;
	}


	public QueryScoreAction getQueryScoreAction() {
		return queryScoreAction;
	}


	public void setQueryScoreAction(QueryScoreAction queryScoreAction) {
		this.queryScoreAction = queryScoreAction;
	}


	public QueryGameAction getQueryGameAction() {
		return queryGameAction;
	}


	public void setQueryGameAction(QueryGameAction queryGameAction) {
		this.queryGameAction = queryGameAction;
	}


	public NewStageAction getNewStageAction() {
		return newStageAction;
	}


	public void setNewStageAction(NewStageAction newStageAction) {
		this.newStageAction = newStageAction;
	}


	public QueryAllStageAction getQueryAllStageAction() {
		return queryAllStageAction;
	}


	public void setQueryAllStageAction(QueryAllStageAction queryAllStageAction) {
		this.queryAllStageAction = queryAllStageAction;
	}


	public QuerySeatAction getQuerySeatAction() {
		return querySeatAction;
	}


	public void setQuerySeatAction(QuerySeatAction querySeatAction) {
		this.querySeatAction = querySeatAction;
	}


	public QueryStageAction getQueryStageAction() {
		return queryStageAction;
	}


	public void setQueryStageAction(QueryStageAction queryStageAction) {
		this.queryStageAction = queryStageAction;
	}


	public JoinStageAction getJoinStageAction() {
		return joinStageAction;
	}


	public void setJoinStageAction(JoinStageAction joinStageAction) {
		this.joinStageAction = joinStageAction;
	}


	public BeginGameAction getBeginGameAction() {
		return beginGameAction;
	}


	public void setBeginGameAction(BeginGameAction beginGameAction) {
		this.beginGameAction = beginGameAction;
	}


	public ConcludeGameAction getConcludeGameAction() {
		return concludeGameAction;
	}


	public void setConcludeGameAction(ConcludeGameAction concludeGameAction) {
		this.concludeGameAction = concludeGameAction;
	}


	public ComputeScoreAction getComputeScoreAction() {
		return computeScoreAction;
	}


	public void setComputeScoreAction(ComputeScoreAction computeScoreAction) {
		this.computeScoreAction = computeScoreAction;
	}


	public QueryPlayerAction getQueryPlayerAction() {
		return queryPlayerAction;
	}


	public void setQueryPlayerAction(QueryPlayerAction queryPlayerAction) {
		this.queryPlayerAction = queryPlayerAction;
	}


	public QueryAllPlayerAction getQueryAllPlayerAction() {
		return queryAllPlayerAction;
	}


	public void setQueryAllPlayerAction(QueryAllPlayerAction queryAllPlayerAction) {
		this.queryAllPlayerAction = queryAllPlayerAction;
	}


	public ChatAction getChatAction() {
		return chatAction;
	}


	public void setChatAction(ChatAction chatAction) {
		this.chatAction = chatAction;
	}


	public GiveGiftAction getGiveGiftAction() {
		return giveGiftAction;
	}


	public void setGiveGiftAction(GiveGiftAction giveGiftAction) {
		this.giveGiftAction = giveGiftAction;
	}


	public CloseStageAction getCloseStageAction() {
		return closeStageAction;
	}


	public void setCloseStageAction(CloseStageAction closeStageAction) {
		this.closeStageAction = closeStageAction;
	}


	public LoginWriteAction getLoginWriteAction() {
		return loginWriteAction;
	}


	public void setLoginWriteAction(LoginWriteAction loginWriteAction) {
		this.loginWriteAction = loginWriteAction;
	}
	
}
