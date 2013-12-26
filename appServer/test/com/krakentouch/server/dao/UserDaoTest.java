package com.krakentouch.server.dao;

import com.krakentouch.server.bean.User;
import com.krakentouch.server.dao.impl.UserDaoImpl;

public class UserDaoTest {

	public static void main(String[] args) {
		User user = new User();
		user.setName("tom");
		user.setAge(12);
		UserDao userDao = new UserDaoImpl();
		userDao.addUser(user);

	}

}
