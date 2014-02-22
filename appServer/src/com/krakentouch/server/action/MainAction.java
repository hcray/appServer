package com.krakentouch.server.action;

import java.util.Map;

import com.krakentouch.server.domain.DeskMap;
import com.krakentouch.server.service.LoginService;
import com.krakentouch.server.tools.Utils;


public class MainAction {
	private LoginService loginService;
	
	public void doCommand(String commandStr){
		Map<String, String> commandMap = Utils.parseCommand(commandStr);
		String address = commandMap.get("address");
		String command = commandMap.get("command");
		String table = commandMap.get("table");
		String value =commandMap.get("value");
		if("login".equals(command)){
			doLogin(commandMap);
		}
	}
	
	public void doLogin(Map<String,String> commandMap){
		String value =commandMap.get("value");
		DeskMap deskMap = new DeskMap();
		deskMap.setDeskID(value);
		loginService.insertDeskMap(deskMap);
	}

	
	
	
	
	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
}
