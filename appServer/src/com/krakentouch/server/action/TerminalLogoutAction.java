package com.krakentouch.server.action;

import java.util.Map;

public interface TerminalLogoutAction {
	public String doCommand(Map<String,String> commandMap);
}
