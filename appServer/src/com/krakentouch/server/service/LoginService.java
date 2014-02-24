package com.krakentouch.server.service;

import com.krakentouch.server.domain.DeskMap;
import com.krakentouch.server.mapper.DeskMapMapper;

public class LoginService {
	private DeskMapMapper deskMapMapper;
	
	/**
	 * 登录
	 * @param deskMap
	 */
	public void insertDeskMap(DeskMap deskMap){
		deskMapMapper.insertDeskMap(deskMap);
	}
	
	/***
	 * 删除登陆记录
	 * @param deskMap
	 */
	public void deleteDeskMap(DeskMap deskMap){
		deskMapMapper.deleteDeskMap(deskMap);
	}


	public DeskMapMapper getDeskMapMapper() {
		return deskMapMapper;
	}


	public void setDeskMapMapper(DeskMapMapper deskMapMapper) {
		this.deskMapMapper = deskMapMapper;
	}
	
}
