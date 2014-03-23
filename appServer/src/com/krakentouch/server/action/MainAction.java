package com.krakentouch.server.action;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.krakentouch.server.bean.CommandBean;
import com.krakentouch.server.domain.PlayerMap;
import com.krakentouch.server.service.LoginService;
import com.krakentouch.server.tools.JaxbUtil;
import com.krakentouch.server.tools.Utils;


public class MainAction {
	//日志
	private static final Logger LOG = LoggerFactory.getLogger(MainAction.class);
	
	private LoginService loginService;
	
	public String doCommand(String commandStr){
		LOG.debug("doCommand(String commandStr) in... " + commandStr);
		String retStr=null;
		Map<String, String> commandMap = Utils.parseCommand(commandStr);
		LOG.debug("commandMap: " + commandMap);
		String command = commandMap.get("Command");
		if("login".equals(command)){
			retStr = doLogin(commandMap);
		}
		LOG.debug("doCommand(String commandStr) out... ");
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
	 * 
	 */
	public String doLogin(Map<String,String> commandMap){
		//TODO 判断是否已经登陆 如果已经登陆的返回已经登陆 其他做的处理
		String retStr = null;
		PlayerMap playerMap = new PlayerMap();
		String playerID = commandMap.get("PlayerID");
		String deskID = commandMap.get("DeskID");
		playerMap.setPlayerID(playerID);
		playerMap.setDeskID(deskID);
		playerMap.setStatus(0);
		loginService.insertPlayerMap(playerMap);
		//返回
		CommandBean commandBean = new CommandBean();
		commandBean.setResult("1");
		commandBean.setCommand("login");
		retStr = JaxbUtil.convertToXml(commandBean, "utf-8");
		return retStr;
	}
	
	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
}
