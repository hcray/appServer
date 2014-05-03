package com.krakentouch.server.action.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.mina.core.session.IoSession;

import com.krakentouch.server.action.TerminalLoginAction;
import com.krakentouch.server.bean.StartupBean;
import com.krakentouch.server.bean.StartupBeanValue;
import com.krakentouch.server.domain.DeskMap;
import com.krakentouch.server.service.LoginService;
import com.krakentouch.server.tools.JaxbUtil;

public class TerminalLoginActionImpl implements TerminalLoginAction {
	
	private LoginService loginService;

	@Override
	public String doCommand(IoSession session, Map<String,String> commandMap) {
		String retStr = null;
		String command = commandMap.get("action");
		//String value = commandMap.get("value");
		
		//Map<String,String> valueMap = Utils.parseCommand(value);
		String deskId = commandMap.get("DeskID");
		String address = commandMap.get("address");
		String category = commandMap.get("category");;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String startTime = sdf.format(new Date());
		DeskMap deskMap = new DeskMap();
		deskMap.setDeskId(deskId);
		deskMap.setStartTime(startTime);
		deskMap.setDelFlag(0); //删除标志（0：没有删除；1：删除）
		deskMap.setMode(0); //工作模式（0~2分别表示单人独占、多人独占、多人分占）
		deskMap.setStatus(0);//工作状态（保留）
		loginService.insertDeskMap(deskMap);
		
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
