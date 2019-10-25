package springbook.user.service;

import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import springbook.user.dao.UserDao;
import springbook.user.domain.Levelu;
import springbook.user.domain.User;

public class UserService {
	public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
	public static final int MIN_RECCOMEND_FOR_GOLD = 30;
	
	private UserDao userDao;
	
	private DataSource dataSource;   //트랜잭션을 위한 dataSource
	
	
	//userDao를 주입받기 위해 setter
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void upgradeLevels() throws Exception {
		TransactionSynchronizationManager.initSynchronization();  
		Connection c = DataSourceUtils.getConnection(dataSource); 
		c.setAutoCommit(false);
		
		try {									   
			List<User> users = userDao.getAll();
			for (User user : users) {
				if (canUpgradeLevel(user)) {
					upgradeLevel(user);
				}
			}
			c.commit();  
		} catch (Exception e) {    
			c.rollback();
			throw e;
		} finally {
			DataSourceUtils.releaseConnection(c, dataSource);	
			TransactionSynchronizationManager.unbindResource(this.dataSource);  
			TransactionSynchronizationManager.clearSynchronization();  
		}
	}

	private boolean canUpgradeLevel(User user) {
		Levelu currentLevel = user.getLevelu();
		
		switch(currentLevel) {
		case BASIC : return (user.getLogin() >= MIN_LOGCOUNT_FOR_SILVER);
		case SILVER : return (user.getRecommend() >= MIN_RECCOMEND_FOR_GOLD);
		case GOLD : return false;
		
		default : throw new IllegalArgumentException("Unknown Level : " + currentLevel);
		
		}
	}

	public void upgradeLevel(User user) {
		user.upgradeLevel();
		userDao.update(user);
	}
	
	public void add(User user) {
		if(user.getLevelu() == null) user.setLevelu(Levelu.BASIC);
		userDao.add(user);
	}
}

