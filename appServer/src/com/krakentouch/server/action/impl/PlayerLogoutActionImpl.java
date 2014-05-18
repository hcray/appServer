package com.krakentouch.server.action.impl;

import java.util.Map;

import org.apache.mina.core.session.IoSession;

import com.krakentouch.server.action.PlayerLogoutAction;
import com.krakentouch.server.service.LoginService;

public class PlayerLogoutActionImpl implements PlayerLogoutAction {
	
	private LoginService loginService;

	@Override
	public String doCommand(IoSession session, Map<String,String> commandMap) {
		String retStr = "";
		//int primaryKey = (Integer) session.getAttribute("primaryKey");
		session.removeAttribute("playerId");
		session.removeAttribute("primaryKey");
		//commandMap.put("primaryKey", primaryKey);
		try{
			retStr = loginService.logout(commandMap);
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
