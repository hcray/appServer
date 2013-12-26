package com.krakentouch.server.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.krakentouch.server.bean.User;
import com.krakentouch.server.dao.UserDao;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

	@Override
	public User addUser(User user) {
		getSqlSession().insert("com.krakentouch.server.mapper.UserMapper.insertUser", user);
		return user;
	}

}
