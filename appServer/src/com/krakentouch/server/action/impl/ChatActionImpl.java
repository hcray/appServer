package com.krakentouch.server.action.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.mina.core.session.IoSession;

import com.krakentouch.server.action.ChatAction;
import com.krakentouch.server.bean.CommandBean;
import com.krakentouch.server.domain.ChatLog;
import com.krakentouch.server.service.ChatService;
import com.krakentouch.server.tools.JaxbUtil;

public class ChatActionImpl implements ChatAction {
	
	private ChatService chatService;

	@Override
	public String doCommand(IoSession session, Map<String,String> commandMap) {
		String retStr = "";
		String command = commandMap.get("Command"); //命令
		String senderID = commandMap.get("SenderID");//发送者
		String recoverID = commandMap.get("RecoverID");//接收者
		String memo = commandMap.get("Memo");//发送内容
		//chatTime
		
		ChatLog chatLog = new ChatLog();
		chatLog.setRecoverID(recoverID);
		chatLog.setSenderID(senderID);
		chatLog.setMemo(memo);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String chatTime = sdf.format(new Date());
		chatLog.setChatTime(chatTime );
		chatService.insertChatLog(chatLog );
		
		CommandBean commandBean = new CommandBean();
		commandBean.setCommand(command);
		commandBean.setResult("1");
		commandBean.setNote("success");
		retStr = JaxbUtil.convertToXml(commandBean, "utf-8");
		
		/*
		ReceiveMessageBean receiveMessageBean = new ReceiveMessageBean();
		receiveMessageBean.setCommand("receiveMessage");
		receiveMessageBean.setSn(chatLog.getSN());
		receiveMessageBean.setTime(chatTime);
		receiveMessageBean.setRecoverId(recoverID);
		receiveMessageBean.setSenderId(senderID);
		receiveMessageBean.setMemo(memo);
		
		String receiveMessage = JaxbUtil.convertToXml(receiveMessageBean, "utf-8");
		*/
		session.write(retStr);
		return retStr;
	}

	public ChatService getChatService() {
		return chatService;
	}

	public void setChatService(ChatService chatService) {
		this.chatService = chatService;
	}
}
