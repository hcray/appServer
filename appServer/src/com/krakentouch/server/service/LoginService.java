package com.krakentouch.server.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.krakentouch.server.bean.CommandBean;
import com.krakentouch.server.bean.QueryScoreCommand;
import com.krakentouch.server.domain.DeskMap;
import com.krakentouch.server.domain.Player;
import com.krakentouch.server.domain.PlayerMap;
import com.krakentouch.server.domain.PlayerScore;
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
	public String login(Map<String, String> commandMap) throws Exception{
		String retStr = null;
		PlayerMap playerMap = new PlayerMap();
		String playerID = commandMap.get("PlayerID");
		String deskID = commandMap.get("DeskID");
		playerMap.setPlayerID(playerID);
		playerMap.setDeskID(deskID);
		playerMap.setStatus(0);
		playerMap.setLoginTime(new Date());
		PlayerMapMapper.insertPlayer(playerMap);
		int id = playerMap.getID(); 
		System.out.println("id:"+id);
		//返回
		CommandBean commandBean = new CommandBean();
		commandBean.setResult("1");
		commandBean.setCommand("login");
		commandBean.setNote("登陆成功");
		commandBean.setPlayerID(playerID);
		commandBean.setDeskID(deskID);
		commandBean.setStatus("0");
		commandBean.setGameID("");
		retStr = JaxbUtil.convertToXml(commandBean, "utf-8");
		return retStr;
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
		playerMap.setPlayerID(playerID);
		playerMap.setLogoutTime(new Date());//退出时间
		playerMap.setDelFlag(1);//删除标志
		
		PlayerMapMapper.updatePlayerStatus(playerMap);
		CommandBean commandBean = new CommandBean();
		commandBean.setResult("1");
		commandBean.setCommand("logout");
		commandBean.setNote("登出成功");
		commandBean.setPlayerID(playerID);
		retStr = JaxbUtil.convertToXml(commandBean, "utf-8");
		
		return retStr;
	}
	
	
	/**
	 * 查分
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	public String queryScore(Map<String, String> commandMap) throws Exception{
		String retStr = null;
		String playerID = commandMap.get("PlayerID");
		
		PlayerScore playerScore = PlayerMapMapper.queryScore(playerID);
		
		QueryScoreCommand queryScoreCommand = new QueryScoreCommand();
		queryScoreCommand.setCommand(commandMap.get("Command"));
		queryScoreCommand.setResult("1");
		queryScoreCommand.setNote("success");
		queryScoreCommand.setPlayerID(playerScore.getPlayerID());
		queryScoreCommand.setScore(String.valueOf(playerScore.getScore()));
		queryScoreCommand.setMoney(String.valueOf(playerScore.getMoney()));
		queryScoreCommand.setProp0(String.valueOf(playerScore.getProp0()));
		retStr = JaxbUtil.convertToXml(queryScoreCommand, "utf-8");
		return retStr;
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

	public DeskMapMapper getDeskMapMapper() {
		return deskMapMapper;
	}


	public void setDeskMapMapper(DeskMapMapper deskMapMapper) {
		this.deskMapMapper = deskMapMapper;
	}
	
}
