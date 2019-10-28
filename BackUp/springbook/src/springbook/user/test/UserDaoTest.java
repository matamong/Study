package springbook.user.test;


import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLErrorCodes;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.jta.SpringJtaSynchronizationAdapter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import springbook.user.dao.UserDao;
import springbook.user.dao.UserDaoJdbc;
import springbook.user.domain.Levelu;
import springbook.user.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)  //테스트가 사용 할 애플리케이션 컨텍스트 만들고 관리해줌
@ContextConfiguration(locations={"file:WebContent/WEB-INF/applicationContext.xml"})   //관리 할 애플리케이션 위치 알려줌 (test dd파일로 옮겨줘따)
public class UserDaoTest {

	@Autowired  // 테스트 오브젝트가 만들어지면 스프링 테스트 컨텍스트에 자동으로 값이 주입된다. 
	private ApplicationContext ac; 

	@Autowired
	DataSource dataSource; 

	@Autowired
	UserDao dao;

	private User user1;
	private User user2;
	private User user3;

	@Before
	public void setUp() {
		//ApplicationContext ac = new GenericXmlApplicationContext("applicationContext.xml");
		//ApplicationContext ac = new AnnotationConfigApplicationContext(DaoFactory.class);

		//this.dao = this.ac.getBean("userDao", UserDao.class);
		this.user1 = new User("bTest1", "Test1", "Test1", Levelu.BASIC, 1, 0, "test1@test");
		this.user2 = new User("cTest2", "Test2", "Test2", Levelu.SILVER, 55, 10, "test2@test");
		this.user3 = new User("aTest3", "Test3", "Test3", Levelu.GOLD, 100, 40, "test3@test");

		System.out.println(this.ac);
		System.out.println(this);
	}

	@Test
	public void addAndGet() throws ClassNotFoundException, SQLException {

		dao.deleteAll();		

		dao.add(user1);
		dao.add(user2);
		assertThat(dao.getCount(), is(2));

		User userGet1 = dao.get(user1.getId());
		checkSameUser(userGet1,  user1);

		User userGet2 = dao.get(user2.getId());
		checkSameUser(userGet2, user2);
	}

	@Test
	public void count() throws SQLException, ClassNotFoundException {

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
	
	@Test
	public void update() {
		dao.deleteAll();
		
		dao.add(user1);
		dao.add(user2);
		
		user1.setName("오민규");
		user1.setPassword("springno6");
		user1.setLevelu(Levelu.GOLD);
		user1.setLogin(1000);
		user1.setRecommend(999);
		user1.setEmail("update@test");
		
		dao.update(user1);
		
		User user1update = dao.get(user1.getId());
		checkSameUser(user1, user1update);
		
		User user2same = dao.get(user2.getId());
		checkSameUser(user2, user2same);
		
	}

	@Test(expected=EmptyResultDataAccessException.class)
	public void getUserFailure() throws SQLException, ClassNotFoundException {

		dao.deleteAll();
		assertThat(dao.getCount(), is(0));

		dao.get("unknown_id");
	}

	//@Test(expected=DataAccessException.class)
	public void sqlExceptionTranslator() {
		dao.deleteAll();

		try {
			
			dao.add(user1);
			dao.add(user1);

		} catch (DuplicateKeyException ex) {
			SQLException sqlEx = (SQLException)ex.getRootCause();
			SQLExceptionTranslator set = 
					new SQLErrorCodeSQLExceptionTranslator(this.dataSource);
			
			assertThat(set.translate(null, null, sqlEx),
					is(DuplicateKeyException.class));
		}

	}

	private void checkSameUser(User user1, User user2) {
		assertThat(user1.getId(), is(user2.getId()));
		assertThat(user1.getName(), is(user2.getName()));
		assertThat(user1.getPassword(), is(user2.getPassword()));
		assertThat(user1.getLevelu(), is(user2.getLevelu()));
		assertThat(user1.getLogin(), is(user2.getLogin()));
		assertThat(user1.getRecommend(), is(user2.getRecommend()));
		assertThat(user1.getEmail(), is(user2.getEmail()));
	}
}
