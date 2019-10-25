package springbook.user.service;

import java.util.List;

import springbook.user.dao.UserDao;
import springbook.user.domain.Levelu;
import springbook.user.domain.User;

public class UserService {
	UserDao userDao;
	
	//userDao를 주입받기 위해 setter
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void upgradeLevels() {
		List<User> users = userDao.getAll();
		
		for(User user : users) {
			if(canUpgradeLevel(user)) {
				upgradeLevel(user);
			}
		}
	}

	private boolean canUpgradeLevel(User user) {
		Levelu currentLevel = user.getLevelu();
		
		switch(currentLevel) {
		case BASIC : return (user.getLogin() >= 50);
		case SILVER : return (user.getRecommend() >= 30);
		case GOLD : return false;
		
		default : throw new IllegalArgumentException("Unknown Level : " + currentLevel);
		
		}
	}

	private void upgradeLevel(User user) {
		user.upgradeLevel();
		userDao.update(user);
	}
	
	public void add(User user) {
		if(user.getLevelu() == null) user.setLevelu(Levelu.BASIC);
		userDao.add(user);
	}
}
