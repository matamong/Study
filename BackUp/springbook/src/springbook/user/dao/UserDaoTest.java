package springbook.user.dao;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import springbook.user.domain.User;

public class UserDaoTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ApplicationContext ac = new GenericXmlApplicationContext("applicationContext.xml");
		
		UserDao dao = ac.getBean("userDao", UserDao.class);
		
		User user = new User();
		user.setId("Refact5");
		user.setName("UseXml");
		user.setPassword("hungry");
		
		dao.add(user);
		
		System.out.println(user.getId() + "등록 성공");
		
		User user2 = dao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
		
		System.out.println(user2.getId() + "등록 성공");
	}
}
