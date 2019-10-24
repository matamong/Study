package springbook.user.test;


import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.jta.SpringJtaSynchronizationAdapter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import springbook.user.dao.UserDao;
import springbook.user.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)  //테스트가 사용 할 애플리케이션 컨텍스트 만들고 관리해줌
@ContextConfiguration(locations="/test-applicationContext.xml")   //관리 할 애플리케이션 위치 알려줌 (test dd파일로 옮겨줘따)
public class UserDaoTest {
	
	@Autowired  // 테스트 오브젝트가 만들어지면 스프링 테스트 컨텍스트에 자동으로 값이 주입된다. 
	private ApplicationContext ac; 
	
	@Autowired
	private UserDao dao;
	
	private User user1;
	private User user2;
	private User user3;
	
	@Before
	public void setUp() {
		//ApplicationContext ac = new GenericXmlApplicationContext("applicationContext.xml");
		//ApplicationContext ac = new AnnotationConfigApplicationContext(DaoFactory.class);
		
		//this.dao = this.ac.getBean("userDao", UserDao.class);
		this.user1 = new User("bTest1", "Test1", "Test1");
		this.user2 = new User("cTest2", "Test2", "Test2");
		this.user3 = new User("aTest3", "Test3", "Test3");
		
		System.out.println(this.ac);
		System.out.println(this);
	}
	
	@Test
	public void addAndGet() throws ClassNotFoundException, SQLException {
		
//		User user1 = new User("aAG1", "aAG1", "aAG1");
//		User user2 = new User("aAG2", "aAG2", "aAG2");
		
		dao.deleteAll();		
		
		dao.add(user1);
		dao.add(user2);
		assertThat(dao.getCount(), is(2));
		
		User userGet1 = dao.get(user1.getId());
		assertThat(userGet1.getName(), is(user1.getName()));
		assertThat(userGet1.getPassword(), is(user1.getPassword()));
		
		User userGet2 = dao.get(user2.getId());
		assertThat(userGet2.getName(), is(user2.getName()));
		assertThat(userGet2.getPassword(), is(user2.getPassword()));
	}
	
	@Test
	public void count() throws SQLException, ClassNotFoundException {
		
//		User user1 = new User("Test1", "Test1", "Test1");
//		User user2 = new User("Test2", "Test2", "Test2");
//		User user3 = new User("Test3", "Test3", "Test3");
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.add(user1);
		assertThat(dao.getCount(), is(1));
		
		dao.add(user2);
		assertThat(dao.getCount(), is(2));

		dao.add(user3);
		assertThat(dao.getCount(), is(3));
	}
	
	@Test
	public void getAll() {
		dao.deleteAll();
		
		dao.add(user1);
		List<User> users1 = dao.getAll();
		assertThat(users1.size(), is(1));
		checkSameUser(user1, users1.get(0));
		
		dao.add(user2);
		List<User> users2 = dao.getAll();
		assertThat(users2.size(), is(2));
		checkSameUser(user1, users2.get(0));
		checkSameUser(user2, users2.get(1));
		
		dao.add(user3);
		List<User> users3 = dao.getAll();
		assertThat(users3.size(), is(3));
		checkSameUser(user3, users3.get(0));
		checkSameUser(user1, users3.get(1));
		checkSameUser(user2, users3.get(2));
		
		dao.deleteAll();
		List<User> users0 = dao.getAll();
		assertThat(users0.size(), is(0));
	}
	
	@Test(expected=EmptyResultDataAccessException.class)
	public void getUserFailure() throws SQLException, ClassNotFoundException {
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.get("unknown_id");
	}
	
	private void checkSameUser(User user1, User user2) {
		assertThat(user1.getId(), is(user2.getId()));
		assertThat(user1.getName(), is(user2.getName()));
		assertThat(user1.getPassword(), is(user2.getPassword()));
	}
}
