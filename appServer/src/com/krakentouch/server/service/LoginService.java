package com.krakentouch.server.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.krakentouch.server.bean.CommandBean;
import com.krakentouch.server.bean.CommandBeanValue;
import com.krakentouch.server.bean.QueryScoreCommand;
import com.krakentouch.server.bean.QueryScoreCommandValue;
import com.krakentouch.server.domain.DeskMap;
import com.krakentouch.server.domain.Player;
import com.krakentouch.server.domain.PlayerInfo;
import com.krakentouch.server.domain.PlayerMap;
import com.krakentouch.server.domain.PlayerScore;
import com.krakentouch.server.domain.TerminalLog;
import com.krakentouch.server.mapper.DeskMapMapper;
import com.krakentouch.server.mapper.PlayerMapMapper;
import com.krakentouch.server.tools.JaxbUtil;

public class LoginService {
	private DeskMapMapper deskMapMapper;

	private PlayerMapMapper PlayerMapMapper;
	
	
	public PlayerMapMapper getPlayerMapMapper() {
		return PlayerMapMapper;
	}

	public void setPlayerMapMapper(PlayerMapMapper playerMapMapper) {
		PlayerMapMapper = playerMapMapper;
	}

	/**
	 * 物理端登录
	 * @param deskMap
	 */
	public void insertDeskMap(DeskMap deskMap){
		deskMapMapper.insertDeskMap(deskMap);
	}
	
	/***
	 * 删除登陆记录
	 * @param deskMap
	 */
	public void deleteDeskMap(DeskMap deskMap){
		deskMapMapper.deleteDeskMap(deskMap);
	}
	
	/**
	 * 更新
	 * @param deskMap
	 */
	public void updateDeskMap(DeskMap deskMap){
		deskMapMapper.updateDeskMap(deskMap);
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
	 * 
	 */
	public PlayerMap login(Map<String, String> commandMap) throws Exception{
		//String retStr = null;
		PlayerMap playerMap = new PlayerMap();
		String playerID = commandMap.get("PlayerID");
		String deskID = commandMap.get("DeskID");
		playerMap.setPlayerID(playerID);
		playerMap.setDeskID(deskID);
		playerMap.setStatus(0);
		playerMap.setLoginTime(new Date());
		PlayerMapMapper.insertPlayer(playerMap);
		
		/*
		System.out.println("id:"+id);
		int id = playerMap.getID(); 
		//返回
		CommandBean commandBean = new CommandBean();
		CommandBeanValue commandBeanValue = new CommandBeanValue();
		
		commandBean.setResult("1");
		commandBean.setCommand("login");
		commandBean.setNote("登陆成功");
		
		commandBeanValue.setPlayerID(playerID);
		commandBeanValue.setDeskID(deskID);
		commandBeanValue.setStatus("0");
		commandBeanValue.setGameID("");
		
		commandBean.setCommandBeanValue(commandBeanValue);
		retStr = JaxbUtil.convertToXml(commandBean, "utf-8");*/
		
		return playerMap;
	}
	
	
	/***
	 * 签出
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	public String logout(Map<String, String> commandMap) throws Exception{
		String retStr = null;
		PlayerMap playerMap = new PlayerMap();
		String playerID = commandMap.get("PlayerID");
		//String primaryKey = commandMap.get("primaryKey");
		
		playerMap.setPlayerID(playerID);
		playerMap.setLogoutTime(new Date());//退出时间
		playerMap.setDelFlag(1);//删除标志
		//playerMap.setID(Integer.parseInt(primaryKey));
		
		PlayerMapMapper.updatePlayerStatus(playerMap);
		
		CommandBean commandBean = new CommandBean();
		CommandBeanValue commandBeanValue = new CommandBeanValue();
		
		commandBean.setResult("1");
		commandBean.setCommand("logout");
		commandBean.setNote("登出成功");
		
		commandBeanValue.setPlayerID(playerID);
		
		commandBean.setCommandBeanValue(commandBeanValue);
		
		retStr = JaxbUtil.convertToXml(commandBean, "utf-8");
		
		return retStr;
	}
	
	
	/**
	 * 查分
	 * @param commandMap
	 * @return String
	 * @throws Exception
	 */
	public String queryScore(Map<String, String> commandMap) throws Exception{
		String retStr = null;
		String playerID = commandMap.get("PlayerID");
		
		PlayerScore playerScore = PlayerMapMapper.queryScore(playerID);
		
		QueryScoreCommand queryScoreCommand = new QueryScoreCommand();
		QueryScoreCommandValue queryScoreCommandValue = new QueryScoreCommandValue();
		
		queryScoreCommand.setCommand(commandMap.get("action"));
		queryScoreCommand.setResult("1");
		queryScoreCommand.setNote("success");
		
		queryScoreCommandValue.setPlayerID(playerScore.getPlayerID());
		queryScoreCommandValue.setScore(String.valueOf(playerScore.getScore()));
		queryScoreCommandValue.setMoney(String.valueOf(playerScore.getMoney()));
		queryScoreCommandValue.setProp0(String.valueOf(playerScore.getProp0()));
		
		queryScoreCommand.setQueryScoreCommandValue(queryScoreCommandValue);
		
		retStr = JaxbUtil.convertToXml(queryScoreCommand, "utf-8");
		return retStr;
	}
	
	/**
	 * 查分
	 * @param commandMap
	 * @return PlayerScore
	 * @throws Exception
	 */
	public PlayerScore queryScoreBean(Map<String, String> commandMap) throws Exception{
		String playerID = commandMap.get("PlayerID");
		PlayerScore playerScore = PlayerMapMapper.queryScore(playerID);
		return playerScore;
	}
	
	/**
	 * 查分
	 * @param commandMap
	 * @return PlayerScore
	 * @throws Exception
	 */
	public PlayerScore queryScoreBean(String playerID) throws Exception{
		PlayerScore playerScore = PlayerMapMapper.queryScore(playerID);
		return playerScore;
	}
	
	
	/**
	 * 更新分数
	 * @param playerScore
	 */
	public void updatePlayerScore(PlayerScore playerScore){
		PlayerMapMapper.updatePlayerScore(playerScore);
	}
	
	/***
	 * 用户登陆
	 * @param playerMap
	 */
	public void insertPlayerMap(PlayerMap playerMap){
		PlayerMapMapper.insertPlayer(playerMap);
	}
	
	
	/***
	 * 用户登出
	 * @param playerMap
	 */
	public void deletePlayerMap(PlayerMap playerMap){
		PlayerMapMapper.deletePlayer(playerMap);
	}
	
	
	/***
	 *  查询用户积分
	 * @param playerID
	 * @return 用户积分
	 */
	public PlayerScore queryScoreByPlayerID(String playerID){
		return PlayerMapMapper.queryScore(playerID);
	}
	
	/**
	 * 更新玩家状态
	 * @param playerMap
	 */
	public void updatePlayerStatus(PlayerMap playerMap){
		PlayerMapMapper.updatePlayerStatus(playerMap);
	}
	
	/**
	 * 查询所有的在线用户
	 * @return
	 */
	public List<Player> queryAllOnlinePlayer(){
		return PlayerMapMapper.queryAllOnlinePlayer();
	}
	
	/***
	 * 根据状态查询用户
	 * @return
	 */
	public List<PlayerMap> selectPlayerByStatus(int status){
		return PlayerMapMapper.selectPlayerByStatus(status);
	}
	
	/***
	 * 插入设备统计
	 * @param terminalLog
	 */
	public int insertTerminalLog(TerminalLog terminalLog){
		deskMapMapper.insertTerminalLog(terminalLog);
		return terminalLog.getSn();
	}
	
	public PlayerInfo queryPlayerInfoById(String playerId){
		return PlayerMapMapper.queryPlayerInfoById(playerId);
	}

	public DeskMapMapper getDeskMapMapper() {
		return deskMapMapper;
	}

	public void setDeskMapMapper(DeskMapMapper deskMapMapper) {
		this.deskMapMapper = deskMapMapper;
	}
	
}
