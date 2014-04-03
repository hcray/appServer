package com.krakentouch.server.service;

import com.krakentouch.server.domain.ChatLog;
import com.krakentouch.server.mapper.ChatLogMapper;

public class ChatService {
	
	private ChatLogMapper chatLogMapper;

	public int insertChatLog(ChatLog chatLog){
		chatLogMapper.insertChatLog(chatLog);
		return chatLog.getSN();
	}
	
	
	public ChatLogMapper getChatLogMapper() {
		return chatLogMapper;
	}

	public void setChatLogMapper(ChatLogMapper chatLogMapper) {
		this.chatLogMapper = chatLogMapper;
	}
	
	
}
