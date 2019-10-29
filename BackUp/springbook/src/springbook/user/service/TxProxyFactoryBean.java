package springbook.user.service;

import java.lang.reflect.Proxy;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

public class TxProxyFactoryBean implements FactoryBean<Object>{
	Object target;
	PlatformTransactionManager transactionManager;
	String pattern;
	//여기까지 TransactionHandler를 생성할 때 필요
	
	//다이내믹 프록시를 생성할 때 필요하다. UserService 외의 인터페이스를 가진 타깃에도 적용 가능.
	Class<?> serviceInterface;
	
	public void setTarget(Object target) {
		this.target = target;
	}
	
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
	
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	public void setServiceInterface(Class<?> serviceInterface) {
		this.serviceInterface = serviceInterface;
	}
	
	
	//FactoryBean 인터페이스 구현
	public Object getObject() throws Exception{
		TransactionHandler txHandler = new TransactionHandler();
		txHandler.setTarget(target);
		txHandler.setTransactionManager(transactionManager);
		txHandler.setPatter(pattern);
		
		return Proxy.newProxyInstance(
				getClass().getClassLoader(), new Class[] {serviceInterface}, txHandler);
	}
	
	public Class<?> getObjectType(){
		return serviceInterface;
	}
	
	public boolean isSingleton() {
		return false;
	}
}
