package com.krakentouch.server.service;

import com.krakentouch.server.domain.User;
import com.krakentouch.server.mapper.UserMapper;


public class UserService {
	private UserMapper userMapper;
	
	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public void insertUser(User user){
		userMapper.insertUser(user);
	}
}
