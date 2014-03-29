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
		try{
			Map<String, String> commandMap = Utils.parseCommand(commandStr);
			LOG.debug("commandMap: " + commandMap);
			String command = commandMap.get("Command");
			if("login".equals(command)){//登录
				retStr = doLogin(commandMap);
			}else if("logout".equals(command)){//注销
				retStr = doLogout(commandMap);
			}else{
				retStr="error,not find command.";
			}
			LOG.debug("doCommand(String commandStr) out... ");
		}catch(Exception e){
			LOG.error(e.getMessage() + e.getCause());
			retStr = "error,"+e.getMessage();
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
	
	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
}
