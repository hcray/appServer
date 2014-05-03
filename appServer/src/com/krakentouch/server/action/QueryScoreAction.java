package com.krakentouch.server.action;

import java.util.Map;

public interface QueryScoreAction {
	public String doCommand(Map<String,String> commandMap);
}
