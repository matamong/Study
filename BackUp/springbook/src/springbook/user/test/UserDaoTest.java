package springbook.user.test;


import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import static org.junit.Assert.*; // is가 안 먹혀서 수동으로 추가
import static org.hamcrest.CoreMatchers.*;  // is가 안 먹혀서 수동으로 추가

import springbook.user.dao.UserDao;
import springbook.user.domain.User;

public class UserDaoTest {
	
	@org.junit.Test
	public void addAndGet() throws ClassNotFoundException, SQLException {
		
		ApplicationContext ac = new GenericXmlApplicationContext("applicationContext.xml");
		//ApplicationContext ac = new AnnotationConfigApplicationContext(DaoFactory.class);
		
		UserDao dao = ac.getBean("userDao", UserDao.class);
		
		User user = new User();
		user.setId("JUnitTest");
		user.setName("First");
		user.setPassword("hungry");
		
		dao.add(user);
		
		User user2 = dao.get(user.getId());
		
		assertThat(user2.getName(), is(user.getName()));
		assertThat(user2.getPassword(), is(user.getPassword()));
	}
	
}
