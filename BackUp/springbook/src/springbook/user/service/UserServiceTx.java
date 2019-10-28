package springbook.user.service;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import springbook.user.domain.User;

public class UserServiceTx implements UserService{

	UserService userService;   //타깃 오브젝트
	PlatformTransactionManager transactionManager;
	
	//UserService를 구현한 다른 오브젝트를 주입받는다.
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
	
	@Override   //DI 받은 UserService 오브젝트에 모든 기능을 위임한다.(메소드 구현과 위임)
	public void add(User user) {
		userService.add(user);
	}
	
	@Override   //메소드 구현
	public void upgradeLevels() {
		//부가기능 수행
		TransactionStatus status = this.transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try {
			//위임
			userService.upgradeLevels();
			
			//부가기능 수행
			this.transactionManager.commit(status);
		} catch (RuntimeException e) {
			this.transactionManager.rollback(status);
			throw e;
		}
	}
}
