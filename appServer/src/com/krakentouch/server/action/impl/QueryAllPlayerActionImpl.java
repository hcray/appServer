package com.krakentouch.server.action.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.mina.core.session.IoSession;

import com.krakentouch.server.action.QueryAllPlayerAction;
import com.krakentouch.server.bean.PlayerBean;
import com.krakentouch.server.bean.PlayerOnline;
import com.krakentouch.server.bean.PlayerOnlineBean;
import com.krakentouch.server.bean.Players;
import com.krakentouch.server.bean.SearchPlayerCommand;
import com.krakentouch.server.domain.Player;
import com.krakentouch.server.domain.PlayerMap;
import com.krakentouch.server.service.LoginService;
import com.krakentouch.server.tools.JaxbUtil;
import com.krakentouch.server.tools.ServerConstants;


/***
 * 发出<TCP tag="QueryAllPlayer" address="OP" category="Social" value="PlayerID=ABCDEFGHIJ"></TCP>，
 * 服务器端反馈以
 * <TCP tag="QueryAllPlayer" address="FB" category="Social" value="PlayerID=ABCDEFGHIJ;Gender=0;Nickname=无名;Icon=0;Grade=0;DeskID=00000000;Status=1;Game=null|PlayerID=CDEFGHIJKL;Gender=1;Nickname=有名;Icon=2;Grade=1;DeskID=00000001;Status=3;Game=abcdef"></TCP>，
 * 
 * 并对该用户以及所有处于询众态的用户（通过查询PlayerMap获知）进行下行通知：
 * <TCP tag="QueryAllPlayer" address="NT" category="Account" value="PlayerID=CDEFGHIJKL;Status=1"></TCP>
 * @author CYY
 *
 */
public class QueryAllPlayerActionImpl implements QueryAllPlayerAction {
	
	private LoginService loginService;

	@Override
	public String doCommand(IoSession session, Map<String,String> commandMap) {
		String retStr = null;
		String command = commandMap.get("Command");
		String playerId = commandMap.get("PlayerID");
		
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
		
		//询众状态的用户
		Set<String> playerIdSetNotify = new HashSet<String>();
		List<PlayerMap> queryPlayers = loginService.selectPlayerByStatus(ServerConstants.playerMap_status_queryPlayers);
		for(PlayerMap player:queryPlayers){
			playerIdSetNotify.add(player.getPlayerID());
		}
		
		PlayerOnlineBean playerOnlineBean = new PlayerOnlineBean();
		playerOnlineBean.setCommand(command);
		playerOnlineBean.setResult("1");
		playerOnlineBean.setNote("success");
		
		PlayerOnline playerOnline = new PlayerOnline();
		//playerOnline.setDeskId(deskId);
		//playerOnline.setGameId("null");
		playerOnline.setPlayerId(playerId);
		playerOnline.setStatus(String.valueOf(ServerConstants.playerMap_status_queryPlayers));
		
		playerOnlineBean.setPlayerOnline(playerOnline);
		
		String notify = JaxbUtil.convertToXml(playerOnlineBean, "utf-8");
		
		
		Collection<IoSession> sessions = session.getService().getManagedSessions().values();
		for(IoSession s : sessions){
			String tempPlayerId = (String) s.getAttribute("playerId");
			if(playerIdSetNotify.contains(tempPlayerId)){
				s.write(notify);
			}
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
