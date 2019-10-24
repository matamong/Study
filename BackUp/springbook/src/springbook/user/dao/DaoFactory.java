package springbook.user.dao;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
public class DaoFactory {
	
	@Bean
	public DataSource dataSource() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		
		dataSource.setDriverClass(oracle.jdbc.driver.OracleDriver.class);
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		dataSource.setUsername("me");
		dataSource.setPassword("tiger");
		
		return dataSource;
	}
	
	@Bean
	public UserDaoJdbc userDao() {
		UserDaoJdbc userDao = new UserDaoJdbc();
		userDao.setDataSource(dataSource());
		return userDao;
	}
	
//	public AccountDao accountDao() {
//		AccountDao accountDao = new AccountDao(connectionMaker());
//		return accountDao;
//	}
//	
//	public MessageDao messageDao() {
//		MessageDao messageDao= new MessageDao(connectionMaker());
//		return messageDao;
//	}
	

}
