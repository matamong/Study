package springbook.user.dao;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import springbook.user.domain.User;

public class UserDaoTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		ApplicationContext ac = new GenericXmlApplicationContext("applicationContext.xml");
		//ApplicationContext ac = new AnnotationConfigApplicationContext(DaoFactory.class);
		
		UserDao dao = ac.getBean("userDao", UserDao.class);
		
		User user = new User();
		user.setId("Test1");
		user.setName("AddTestFail");
		user.setPassword("hungry");
		
		dao.add(user);
		
		System.out.println(user.getId() + "등록되었습니다.");
		
		User user2 = dao.get(user.getId());
		
		
//		System.out.println(user2.getName());
//		System.out.println(user2.getPassword());
//		System.out.println(user2.getId() + "등록되었습니다.");
		
		if(!user.getName().equals(user2.getName())) {
			System.out.println("테스트 실패 (name)");
		}else if(!user.getPassword().equals(user2.getPassword())) {
			System.out.println("테스트 실패(password)");
		}else {
			System.out.println("조회 테스트 성공");
		}
		
	}
}
