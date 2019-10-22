package springbook.user.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
	
	
	public static void main(String[] args) {
		DaoFactory factory = new DaoFactory();
		
		UserDao dao1 = factory.userDao();
		UserDao dao2 = factory.userDao();
		
		System.out.println(dao1);
		System.out.println(dao2);
		
		ApplicationContext ac = new AnnotationConfigApplicationContext(DaoFactory.class);
		
		UserDao dao3 = ac.getBean("userDao", UserDao.class);
		UserDao dao4 = ac.getBean("userDao", UserDao.class);
		
		System.out.println(dao3);
		System.out.println(dao4);
	}

}
