package com.krakentouch.server.action.impl;

import java.util.Map;

import com.krakentouch.server.action.PlayerLogoutAction;
import com.krakentouch.server.service.LoginService;

public class PlayerLogoutActionImpl implements PlayerLogoutAction {
	
	private LoginService loginService;

	@Override
	public String doCommand(Map<String,String> commandMap) {
		String retStr = "";
		try{
			retStr = loginService.logout(commandMap);
		}catch(Exception e){
			e.printStackTrace();
		}
		return retStr;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

}
