package com.krakentouch.server.action.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.mina.core.session.IoSession;

import com.krakentouch.server.action.GiveGiftAction;
import com.krakentouch.server.bean.ReceiveMessageBean;
import com.krakentouch.server.domain.ChatLog;
import com.krakentouch.server.domain.PlayerScore;
import com.krakentouch.server.service.ChatService;
import com.krakentouch.server.service.LoginService;
import com.krakentouch.server.tools.JaxbUtil;

public class GiveGiftActionImpl implements GiveGiftAction {
	
	private LoginService loginService;
	
	private ChatService chatService;

	@Override
	public String doCommand(IoSession session, Map<String,String> commandMap) {
		String senderPresent = "";
		String command = commandMap.get("Command"); //命令
		String senderID = commandMap.get("SenderID");//发送者
		String recoverID = commandMap.get("RecoverID");//接收者
		int score = Integer.parseInt(commandMap.get("Score"));//分数
		int money = Integer.parseInt(commandMap.get("Money"));//金钱
		int prop0 = Integer.parseInt(commandMap.get("Prop0"));//道具
		
		try {
			PlayerScore playerScore = loginService.queryScoreBean(senderID);
			//PlayerScore recoverScore = loginService.queryScoreBean(recoverID);
			if(playerScore.getScore() < score || playerScore.getMoney() < money || playerScore.getProp0() < prop0){
				senderPresent = "score or money or prop0 is not enough";
			}else{
				//发送者减少
				playerScore.setScore(playerScore.getScore() - score);
				playerScore.setMoney(playerScore.getMoney() - money);
				playerScore.setProp0(playerScore.getProp0() - prop0);
				loginService.updatePlayerScore(playerScore);
				
				ChatLog chatLog = new ChatLog();
				chatLog.setSenderID("0000000000"); //系统发送
				chatLog.setRecoverID(senderID);
				StringBuffer memoBuffer = new StringBuffer();
				memoBuffer.append("您赠送 ").append(recoverID);
				if(score != 0) memoBuffer.append(" score:").append(score);
				if(money != 0) memoBuffer.append(" money:").append(money);
				if(prop0 != 0) memoBuffer.append(" prop0:").append(prop0);
				chatLog.setMemo(memoBuffer.toString());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				String chatTime = sdf.format(new Date());
				chatLog.setChatTime(chatTime);
				chatService.insertChatLog(chatLog );
				
				ReceiveMessageBean senderPresentBean = new ReceiveMessageBean();
				senderPresentBean.setCommand(command);
				senderPresentBean.setSn(chatLog.getSN());
				senderPresentBean.setTime(chatLog.getChatTime());
				senderPresentBean.setRecoverId(chatLog.getRecoverID());
				senderPresentBean.setSenderId(chatLog.getSenderID());
				senderPresentBean.setMemo(chatLog.getMemo());
				
				senderPresent = JaxbUtil.convertToXml(senderPresentBean, "utf-8");
				
				//接受者增加
				
				/*recoverScore.setScore(recoverScore.getScore() + score);
			recoverScore.setMoney(recoverScore.getMoney() + money);
			recoverScore.setProp0(recoverScore.getProp0() + prop0);
			loginService.updatePlayerScore(recoverScore);
			chatLog.setRecoverID(recoverID);
			StringBuffer rMemoBuffer = new StringBuffer();
			rMemoBuffer.append(senderID).append("赠送您 ");
			if(score != 0) rMemoBuffer.append(" score:").append(score);
			if(money != 0) rMemoBuffer.append(" money:").append(money);
			if(prop0 != 0) rMemoBuffer.append(" prop0:").append(prop0);
			chatLog.setMemo(rMemoBuffer.toString());
			chatService.insertChatLog(chatLog);
			
			ReceiveMessageBean receivePresentBean = new ReceiveMessageBean();
			receivePresentBean.setCommand("receivePresent");
			receivePresentBean.setSn(chatLog.getSN());
			receivePresentBean.setTime(chatLog.getChatTime());
			receivePresentBean.setRecoverId(chatLog.getRecoverID());
			receivePresentBean.setSenderId(chatLog.getSenderID());
			receivePresentBean.setMemo(chatLog.getMemo());
			String receivePresent = JaxbUtil.convertToXml(receivePresentBean, "utf-8");
				 */
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.write(senderPresent);
		return senderPresent;
	}

	public ChatService getChatService() {
		return chatService;
	}

	public void setChatService(ChatService chatService) {
		this.chatService = chatService;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
}
