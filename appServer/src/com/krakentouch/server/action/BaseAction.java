package com.krakentouch.server.action;

import java.util.Map;

public interface BaseAction {
	public String doCommand(Map<String,String> commandMap);
}
