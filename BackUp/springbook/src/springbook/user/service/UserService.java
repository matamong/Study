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
			Boolean changed = null;
			
			if(user.getLevelu() == Levelu.BASIC && user.getLogin() >= 50) {
				user.setLevelu(Levelu.SILVER);
				changed = true;
			}
			else if(user.getLevelu() == Levelu.SILVER && user.getRecommend() >= 30) {
				user.setLevelu(Levelu.GOLD);
				changed = true;
			}
			else if(user.getLevelu() == Levelu.GOLD) { changed = false; }
			else {changed = false;}
			
			if(changed) { userDao.update(user);}
		}
	}
	
	public void add(User user) {
		if(user.getLevelu() == null) user.setLevelu(Levelu.BASIC);
		userDao.add(user);
	}
}
