package springbook.user.service;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.MockitoJUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;

import javafx.beans.binding.When;
import springbook.user.dao.UserDao;
import springbook.user.domain.Levelu;
import springbook.user.domain.User;
import sun.util.logging.PlatformLogger.Level;

import static springbook.user.service.UserServiceImpl.MIN_LOGCOUNT_FOR_SILVER;
import static springbook.user.service.UserServiceImpl.MIN_RECCOMEND_FOR_GOLD;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:WebContent/WEB-INF/test-applicationContext.xml"})
public class UserServiceTest {
	
	@Autowired UserDao userDao;   //dao도 주입시켜줘야 dao를 쓸 수 있다!
	
	@Autowired UserService userService;
	
	@Autowired UserServiceImpl userServiceImpl;
	
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
	public void upgradeLevels() throws Exception {

		//고립된 테스트에서는 테스트 대상 오브젝트를 직접 생성하면 된다.
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		
		//목 오브젝트 생성, 메소드 리턴 값 설정, DI
		UserDao mockUserDao = mock(UserDao.class);
		when(mockUserDao.getAll()).thenReturn(this.users);
		userServiceImpl.setUserDao(mockUserDao);
		
		//return 값이 없는 목 오브젝트 생성
		MailSender mockMailSender = mock(MailSender.class);
		userServiceImpl.setMailSender(mockMailSender);
		
		userServiceImpl.upgradeLevels();
		
		//어떤 메소드가 몇 번 호출 됐는지, 파라미터는 무엇인지 확인
		verify(mockUserDao, times(2)).update(any(User.class));
		verify(mockUserDao, times(2)).update(any(User.class));
		verify(mockUserDao).update(users.get(1));
		assertThat(users.get(1).getLevelu(), is(Levelu.SILVER));
		verify(mockUserDao).update(users.get(3));
		assertThat(users.get(3).getLevelu(), is(Levelu.GOLD));
		
		//파라미터를 정밀하게 검사하기 위해 캡처.
		ArgumentCaptor<SimpleMailMessage> mailMessageArg =
				ArgumentCaptor.forClass(SimpleMailMessage.class);
		verify(mockMailSender, times(2)).send(mailMessageArg.capture());
		
		List<SimpleMailMessage> mailMessages = mailMessageArg.getAllValues();
		assertThat(mailMessages.get(0).getTo()[0], is(users.get(1).getEmail()));
		assertThat(mailMessages.get(1).getTo()[0], is(users.get(3).getEmail()));
		
	}
	
	private void checkUserAndLevel(User updated, String expectedId, Levelu expectedLevel) {
		assertThat(updated.getId(), is(expectedId));
		assertThat(updated.getLevelu(), is(expectedLevel));
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
		testUserService.setMailSender(mailSender);
		
		//트랜잭션 핸들러가 필요한 정보와 오브젝트를 DI해준다.
		TransactionHandler txHandler = new TransactionHandler();
		txHandler.setTarget(testUserService);
		txHandler.setTransactionManager(transactionManager);
		txHandler.setPatter("upgradeLevels");
		
		//UserService 인터페이스 타입의 다이내믹 프록시 생성
		UserService txUserService = (UserService)Proxy.newProxyInstance(
				getClass().getClassLoader(), new Class[] {UserService.class}, txHandler);
		
		userDao.deleteAll();			  
		for(User user : users) userDao.add(user);
		
		try {
			txUserService.upgradeLevels();   
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
	
	static class MockUserDao implements UserDao{
		//레벨 업그레이드 후보 User오브젝트 목록
		private List<User> users;

		//업그레이드 대상 오브젝트를 저장해둘 목록
		private List<User> updated = new ArrayList();
		
		private MockUserDao(List<User> users) {
			this.users = users;
		}
		
		public List<User> getUpdated(){
			return this.updated;
		}
		
		//스텁 기능 제공
		public List<User> getAll(){
			return this.users;
		}
		
		//목 오브젝트 기능
		public void update(User user) {
			updated.add(user);
		}
		
		
		//UserDao를 implements하면서 쓰이지 않는 메소드
		public void add(User user) {throw new UnsupportedOperationException();}
		public void deleteAll() {throw new UnsupportedOperationException();}
		public User get(String id) {throw new UnsupportedOperationException();}
		public int getCount() {throw new UnsupportedOperationException();}
	}
}

