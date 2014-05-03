package com.krakentouch.server.action.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.krakentouch.server.action.TerminalLogoutAction;
import com.krakentouch.server.bean.StartupBean;
import com.krakentouch.server.bean.StartupBeanValue;
import com.krakentouch.server.domain.DeskMap;
import com.krakentouch.server.service.LoginService;
import com.krakentouch.server.tools.JaxbUtil;

public class TerminalLogoutActionImpl implements TerminalLogoutAction {
	
	private LoginService loginService;

	@Override
	public String doCommand(Map<String,String> commandMap) {
		String retStr = null;
		String command = commandMap.get("action");
		String deskId = commandMap.get("DeskID");
		String address = commandMap.get("address");
		String category = commandMap.get("category");;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String shutdownTime = sdf.format(new Date());
		DeskMap deskMap = new DeskMap();
		deskMap.setDeskId(deskId);
		deskMap.setShutdownTime(shutdownTime);
		deskMap.setDelFlag(1); //删除标志（0：没有删除；1：删除）
		deskMap.setStatus(0);//工作状态（保留）
		loginService.updateDeskMap(deskMap);
		
		StartupBean startupBean = new StartupBean();
		StartupBeanValue value = new StartupBeanValue();
		startupBean.setCommand(command);
		startupBean.setResult("1");
		startupBean.setNote("success");
		value.setDeskId(deskId);
		value.setMode(0);
		value.setStatus(0);
		startupBean.setValue(value);
		startupBean.setAddress(address);
		startupBean.setCategory(category);
		
		retStr = JaxbUtil.convertToXml(startupBean, "utf-8");
		return retStr;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

}
