package com.krakentouch.server.action.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.mina.core.session.IoSession;

import com.krakentouch.server.action.LoginWriteAction;
import com.krakentouch.server.bean.LoginWriteBean;
import com.krakentouch.server.bean.LoginWriteBeanValue;
import com.krakentouch.server.domain.TerminalLog;
import com.krakentouch.server.service.LoginService;
import com.krakentouch.server.tools.JaxbUtil;

public class LoginWriteActionImpl implements LoginWriteAction {
	
	private LoginService loginService;

	@Override
	public String doCommand(IoSession session, Map<String,String> commandMap) {
		String retStr = null;
		String command = commandMap.get("action");
		//String value = commandMap.get("value");
		
		//Map<String,String> valueMap = Utils.parseCommand(value);
		String deskId = commandMap.get("DeskID");
		String script = commandMap.get("Script");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String time = sdf.format(new Date());
		
		TerminalLog terminalLog = new TerminalLog();
		terminalLog.setDeskId(deskId);
		terminalLog.setScript(script);
		terminalLog.setTime(time);
		
		loginService.insertTerminalLog(terminalLog );
		
		LoginWriteBean loginWriteBean = new LoginWriteBean();
		loginWriteBean.setCommand(command);
		loginWriteBean.setResult("1");
		loginWriteBean.setNote("success");
		
		LoginWriteBeanValue value = new LoginWriteBeanValue();
		value.setDeskId(deskId);
		value.setScript(script);
		value.setTime(time);
		value.setSn(terminalLog.getSn());
		
		loginWriteBean.setValue(value);
		
		
		retStr = JaxbUtil.convertToXml(loginWriteBean, "utf-8");
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
