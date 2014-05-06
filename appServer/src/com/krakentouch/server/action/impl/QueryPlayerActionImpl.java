package com.krakentouch.server.action.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.mina.core.session.IoSession;

import com.krakentouch.server.action.QueryPlayerAction;
import com.krakentouch.server.bean.PlayerBean;
import com.krakentouch.server.bean.Players;
import com.krakentouch.server.bean.SearchPlayerCommand;
import com.krakentouch.server.domain.Player;
import com.krakentouch.server.service.LoginService;
import com.krakentouch.server.tools.JaxbUtil;

public class QueryPlayerActionImpl implements QueryPlayerAction {
	
	private LoginService loginService;

	@Override
	public String doCommand(IoSession session, Map<String,String> commandMap) {
		String retStr = null;
		String command = commandMap.get("Command");
		
		List<Player> playerlist = loginService.queryAllOnlinePlayer();
		List<PlayerBean> playerBeans = new ArrayList<PlayerBean>();
		for(Player p : playerlist){
			PlayerBean playerBean = new PlayerBean();
			playerBean.setPlayerID(p.getPlayerID());
			playerBean.setDeskID(p.getDeskID());
			playerBean.setGender(p.getGender());
			playerBean.setGrade(p.getGrade());
			playerBean.setIcon(p.getIcon());
			playerBean.setNickName(p.getNickName());
			playerBeans.add(playerBean);
		}
		
		Players players =  new Players();
		players.setPlayers(playerBeans);
		
		SearchPlayerCommand searchPlayerCommand = new SearchPlayerCommand();
		searchPlayerCommand.setCommand(command);
		searchPlayerCommand.setResult("1");
		searchPlayerCommand.setNote("success");
		searchPlayerCommand.setPlayers(players);
		
		retStr = JaxbUtil.convertToXml(searchPlayerCommand, "utf-8");
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
