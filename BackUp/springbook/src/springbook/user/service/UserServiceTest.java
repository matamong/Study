package springbook.user.service;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.hamcrest.CoreMatchers.is;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;



import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;

import springbook.user.dao.UserDao;
import springbook.user.domain.Levelu;
import springbook.user.domain.User;

import static springbook.user.service.UserServiceImpl.MIN_LOGCOUNT_FOR_SILVER;
import static springbook.user.service.UserServiceImpl.MIN_RECCOMEND_FOR_GOLD;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:WebContent/WEB-INF/test-applicationContext.xml"})
public class UserServiceTest {
	
	@Autowired UserDao userDao;   //dao도 주입시켜줘야 dao를 쓸 수 있다!
	
	@Autowired UserServiceImpl userService;
	
	@Autowired DataSource dataSource;
	
	@Autowired PlatformTransactionManager transactionManager;
	
	@Autowired MailSender mailSender;

	List<User> users;
	
	
	@Before
	public void setUp() {
		users = Arrays.asList(
				new User("girl1", "걸1", "p1", Levelu.BASIC, MIN_LOGCOUNT_FOR_SILVER-1,0, "test1@test"),
				new User("girl2", "걸2", "p2", Levelu.BASIC, MIN_LOGCOUNT_FOR_SILVER, 0, "test2@test"),
				new User("girl3", "걸3", "p3", Levelu.SILVER, 60, MIN_RECCOMEND_FOR_GOLD-1, "test3@test"),
				new User("girl4", "걸4", "p4", Levelu.SILVER, 60, MIN_RECCOMEND_FOR_GOLD, "test4@test"),
				new User("girl5", "걸5", "p5", Levelu.GOLD, 100, Integer.MAX_VALUE, "test5@test")
				);
		//테스트 값은 경계가 되는 값의 전후로 선택하는 것이 좋다.
	}
	
	@Test
	@DirtiesContext
	public void upgradeLevels() throws Exception {
		userDao.deleteAll();
		for(User user : users) userDao.add(user);
		
		MockMailSender mockMailSender = new MockMailSender();
		userService.setMailSender(mockMailSender);
		
		userService.upgradeLevels();
		
		checkLevelUpgraded(users.get(0), false);
		checkLevelUpgraded(users.get(1), true);
		checkLevelUpgraded(users.get(2), false);
		checkLevelUpgraded(users.get(3), true);
		checkLevelUpgraded(users.get(4), false);
		
		List<String> request = mockMailSender.getRequests();
		assertThat(request.size(), is(2));
		assertThat(request.get(0), is(users.get(1).getEmail()));
		assertThat(request.get(1), is(users.get(3).getEmail()));
	}
	
	@Test
	public void add() {
		userDao.deleteAll();
		
		User userWithLevel = users.get(4);
		User userWithoutLevel = users.get(0);
		userWithoutLevel.setLevelu(null);
		
		userService.add(userWithLevel);
		userService.add(userWithoutLevel);
		
		User userWithLevelRead = userDao.get(userWithLevel.getId());
		User userWithoutLevelRead = userDao.get(userWithoutLevel.getId());
				
		assertThat(userWithLevelRead.getLevelu(), is(userWithLevel.getLevelu()));
		assertThat(userWithoutLevel.getLevelu(), is(Levelu.BASIC));
	}
	
	
	private void checkLevelUpgraded(User user, boolean upgraded) {
		User userUpdate = userDao.get(user.getId());
		if(upgraded) {
			assertThat(userUpdate.getLevelu(), is(user.getLevelu().nextLevel()));
		}
		else {
			assertThat(userUpdate.getLevelu(), is(user.getLevelu()));
		}
	}
	
	@Test
	public void upgradeAllOrNothing() throws Exception {
		UserServiceImpl testUserService = new TestUserService(users.get(3).getId());  
		testUserService.setUserDao(userDao); 
		testUserService.setTransactionManager(transactionManager);
		testUserService.setMailSender(mailSender);
		 
		userDao.deleteAll();			  
		for(User user : users) userDao.add(user);
		
		try {
			testUserService.upgradeLevels();   
			fail("TestUserServiceException expected"); 
		}
		catch(TestUserServiceException e) { 
		}
		
		checkLevelUpgraded(users.get(1), false);
		testUserService.setMailSender(mailSender);
	}
	
	
	
	//test용
	static class TestUserService extends UserServiceImpl{
		private String id;
		
		private TestUserService(String id) {
			this.id = id;
		}
		
		@Override
		public void upgradeLevel(User user) {
			if(user.getId().equals(this.id)) throw new TestUserServiceException();
			super.upgradeLevel(user);
		}
	}
	
	static class TestUserServiceException extends RuntimeException{
	}
	
	static class MockMailSender implements MailSender{
		private List<String> requests = new ArrayList<String>();
		
		public List<String> getRequests(){
			return requests;
		}
		
		public void send(SimpleMailMessage mailMessage) throws MailException{
			requests.add(mailMessage.getTo()[0]);
		}
		
		public void send(SimpleMailMessage[] mailMessage) throws MailException{
			
		}
	}
}

