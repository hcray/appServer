package com.krakentouch.server.action.impl;

import java.util.Map;

import org.apache.mina.core.session.IoSession;

import com.krakentouch.server.action.ComputeScoreAction;
import com.krakentouch.server.domain.PlayerScore;
import com.krakentouch.server.service.LoginService;
/***
 * 算分：然后所有用户发出
 * <TCP action="ComputeScore" address="OP" category="Game" value="PlayerID=ABCDEFGHIJ;Score=0;Money=0;Prop0..."></TCP>等等，
 * 服务器端更新PlayerScore表的对应记录并反馈以
 * <TCP action="ComputeScore" address="FB" category="Game" value="PlayerID=ABCDEFGHIJ;Score=0;Money=0;Prop0..."></TCP>等等
 * @author 21829
 *
 */
public class ComputeScoreActionImpl implements ComputeScoreAction {
	
	private LoginService loginService;

	@Override
	public String doCommand(IoSession session, Map<String,String> commandMap) {
		String retStr = "";
		String command = commandMap.get("action");
		String playerId = commandMap.get("PlayerID");
		String score = commandMap.get("Score");
		String money = commandMap.get("Money");
		String prop0 = commandMap.get("Prop0");
		
		try{
			PlayerScore playerScore = new PlayerScore();
			playerScore.setMoney(Integer.parseInt(money));
			playerScore.setPlayerID(playerId);
			playerScore.setScore(Integer.parseInt(score));
			playerScore.setProp0(Integer.parseInt(prop0));
			
			loginService.updatePlayerScore(playerScore);
		}catch(Exception e){
			e.printStackTrace();
		}
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
