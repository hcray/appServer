package com.krakentouch.server.action.impl;

import java.util.Map;

import org.apache.mina.core.session.IoSession;

import com.krakentouch.server.action.PlayerLoginAction;
import com.krakentouch.server.service.LoginService;

public class PlayerLoginActionImpl implements PlayerLoginAction {
	
	private LoginService loginService;

	@Override
	public String doCommand(IoSession session, Map<String,String> commandMap) {
		String retStr = "";
		String playerId = commandMap.get("PlayerID");
		session.setAttribute("playerId", playerId);
		try{
			retStr = loginService.login(commandMap);
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
