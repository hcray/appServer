package com.krakentouch.server.action.impl;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.apache.mina.core.session.IoSession;

import com.krakentouch.server.action.ChatAction;
import com.krakentouch.server.bean.ChatMessageBean;
import com.krakentouch.server.bean.ChatMessageBeanValue;
import com.krakentouch.server.domain.ChatLog;
import com.krakentouch.server.service.ChatService;
import com.krakentouch.server.tools.JaxbUtil;

/**
 * 由发送方用户发出
 * <TCP tag="Chat" address="OP" category="Social" value="SenderID=ABCDEFGHIJ;RecverID=CDEFGHIJKL;Memo=哈喽"></TCP>，
 * 
 * 服务器端在ChatLog表中新增一条对应记录并反馈给发送方：
 * <TCP tag="Chat" address="FB" category="Social" value="SN=0;Time=xxxxxxxx;SenderID=ABCDEFGHIJ;RecverID=CDEFGHIJKL;Memo=哈喽"></TCP>、
 * 
 * 接收方用户：
 * <TCP tag="Chat" address="NT" category="Social" value="SN=0;Time=xxxxxxxx;SenderID=ABCDEFGHIJ;RecverID=CDEFGHIJKL;Memo=哈喽"></TCP>
 * @author CYY
 *
 */
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
		
		ChatMessageBean chatMessageBean = new ChatMessageBean();
		chatMessageBean.setCommand(command);
		chatMessageBean.setResult("1");
		chatMessageBean.setNote("success");
		
		ChatMessageBeanValue chatMessageBeanValue = new ChatMessageBeanValue();
		chatMessageBeanValue.setSn(chatLog.getSN());
		chatMessageBeanValue.setTime(chatTime);
		chatMessageBeanValue.setRecoverId(recoverID);
		chatMessageBeanValue.setSenderId(senderID);
		chatMessageBeanValue.setMemo(memo);
		
		chatMessageBean.setChatMessageBeanValue(chatMessageBeanValue);
		
		retStr = JaxbUtil.convertToXml(chatMessageBean, "utf-8");
		session.write(retStr);
		
		Collection<IoSession> sessions = session.getService().getManagedSessions().values();
		
		for(IoSession s : sessions){
			String tempPlayerId = (String) s.getAttribute("playerId");
			if(recoverID.equalsIgnoreCase(tempPlayerId)){
				s.write(retStr);
			}
		}
		
		return retStr;
	}

	public ChatService getChatService() {
		return chatService;
	}

	public void setChatService(ChatService chatService) {
		this.chatService = chatService;
	}
}
