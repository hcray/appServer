package com.krakentouch.server.action.impl;

import java.util.Map;

import org.apache.mina.core.session.IoSession;

import com.krakentouch.server.action.PlayerLoginAction;
import com.krakentouch.server.bean.CommandBean;
import com.krakentouch.server.bean.CommandBeanValue;
import com.krakentouch.server.domain.PlayerMap;
import com.krakentouch.server.service.LoginService;
import com.krakentouch.server.tools.JaxbUtil;

public class PlayerLoginActionImpl implements PlayerLoginAction {
	
	private LoginService loginService;

	@Override
	public String doCommand(IoSession session, Map<String,String> commandMap) {
		String retStr = "";
		String playerId = commandMap.get("PlayerID");
		session.setAttribute("playerId", playerId);
		try{
			PlayerMap playerMap = loginService.login(commandMap);
			int primaryKey = playerMap.getID();
			session.setAttribute("primaryKey", primaryKey);
			
			//返回
			CommandBean commandBean = new CommandBean();
			CommandBeanValue commandBeanValue = new CommandBeanValue();
			
			commandBean.setResult("1");
			commandBean.setCommand("login");
			commandBean.setNote("登陆成功");
			
			commandBeanValue.setPlayerID(playerMap.getPlayerID());
			commandBeanValue.setDeskID(playerMap.getDeskID());
			commandBeanValue.setStatus(String.valueOf(playerMap.getStatus()));
			commandBeanValue.setGameID(playerMap.getGameID());
			
			commandBean.setCommandBeanValue(commandBeanValue);
			retStr = JaxbUtil.convertToXml(commandBean, "utf-8");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		session.write(retStr);
		return retStr;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

}
