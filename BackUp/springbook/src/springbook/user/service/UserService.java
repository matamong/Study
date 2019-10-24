package springbook.user.service;

import springbook.user.dao.UserDao;

public class UserService {
	UserDao userDao;
	
	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}

}
