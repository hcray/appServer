package com.krakentouch.server.action.impl;

import java.util.Map;

import org.apache.mina.core.session.IoSession;

import com.krakentouch.server.action.ComputeScoreAction;
import com.krakentouch.server.bean.ComputeScoreBean;
import com.krakentouch.server.bean.ComputeScoreBeanValue;
import com.krakentouch.server.domain.PlayerScore;
import com.krakentouch.server.service.LoginService;
import com.krakentouch.server.tools.JaxbUtil;
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
			//原来的分数
			PlayerScore oldPlayerScore = loginService.queryScoreBean(playerId);
			
			PlayerScore playerScore = new PlayerScore();
			playerScore.setPlayerID(playerId);
			playerScore.setMoney(oldPlayerScore.getMoney() + Integer.parseInt(money));
			playerScore.setScore(oldPlayerScore.getScore() + Integer.parseInt(score));
			playerScore.setProp0(oldPlayerScore.getProp0() + Integer.parseInt(prop0));
			
			loginService.updatePlayerScore(playerScore);
			
			ComputeScoreBean computeScoreBean = new ComputeScoreBean();
			computeScoreBean.setCommand(command);
			computeScoreBean.setResult("1");
			computeScoreBean.setNote("success");
			
			ComputeScoreBeanValue computeScoreBeanValue = new ComputeScoreBeanValue();
			computeScoreBeanValue.setMoney(money);
			computeScoreBeanValue.setPlayerID(playerId);
			computeScoreBeanValue.setScore(score);
			computeScoreBeanValue.setProp0(prop0);
			computeScoreBean.setComputeScoreBeanValue(computeScoreBeanValue);
			
			retStr = JaxbUtil.convertToXml(computeScoreBean, "utf-8");
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
