package com.krakentouch.server.action;

import java.util.Map;

import com.krakentouch.server.service.LoginService;

public class PlayerLoginAction implements BaseAction {
	
	private LoginService loginService;

	@Override
	public String doCommand(Map<String,String> commandMap) {
		String retStr = "";
		try{
			retStr = loginService.login(commandMap);
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
