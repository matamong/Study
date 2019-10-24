package springbook.user.dao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserDaoConnectionCountingTest {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(CountingDaoFactory.class);
		
		UserDaoJdbc dao = ac.getBean("userDao", UserDaoJdbc.class);
		
		CountingConnectionMaker ccm = ac.getBean("connectionMaker", CountingConnectionMaker.class);
		
		System.out.println("Connection counter : " + ccm.getCounter());
	}

}
