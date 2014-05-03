package com.krakentouch.server.action;

import java.util.Map;

public interface TerminalLoginAction {
	public String doCommand(Map<String,String> commandMap);
}
