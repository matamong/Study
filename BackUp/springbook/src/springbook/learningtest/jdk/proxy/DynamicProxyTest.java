package springbook.learningtest.jdk.proxy;

import static org.junit.Assert.assertThat;

import java.lang.reflect.Proxy;

import static org.hamcrest.CoreMatchers.is;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

import springbook.learningtest.template.HelloTarget;
import springbook.learningtest.template.UppercaseHandler;

public class DynamicProxyTest {
	
	@Test
	public void classnamePointcutAdvisor() {
		//포인트컷 준비 (익명 내부 클래스 방식으로 클래스를 정의한다.)
		NameMatchMethodPointcut classMethodPointcut = new NameMatchMethodPointcut() {
			public ClassFilter getClassFilter() {
				return new ClassFilter() {
					public boolean matches(Class<?> clazz) {
						return clazz.getSimpleName().startsWith("HelloT"); //클래스 이름이 HelloT로 시작하는 것만 선정.
					}
				};
			}
		};
		
		classMethodPointcut.setMappedName("sayH*" );  //sayH로 시작하는 메소드 이름을 가진 메소드만 선정.
		
		//테스트
		checkAdviced(new HelloTarget(), classMethodPointcut, true);		//적용 클래스 테스트
		
		class HelloWorld extends HelloTarget{};
		checkAdviced(new HelloWorld(), classMethodPointcut, false);		//적용 클래스 아닌거 테스트
		
		class HelloToby extends HelloTarget{};
		checkAdviced(new HelloToby(), classMethodPointcut, true);		//적용 클래스 테스트
	}
	
	private void checkAdviced(Object target, Pointcut pointcut, boolean adviced) {
		ProxyFactoryBean pfBean = new ProxyFactoryBean();
		
		pfBean.setTarget(target);
		pfBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, new UppercaseAdvice()));
		Hello proxciedHello = (Hello)pfBean.getObject();
		
		if(adviced) {
			assertThat(proxciedHello.sayHello("Toby"), is("HELLO TOBY"));
			assertThat(proxciedHello.sayHi("Toby"), is("HI TOBY"));
			assertThat(proxciedHello.sayThankYou("Toby"), is("Thank You Toby"));
		}else {
			assertThat(proxciedHello.sayHello("Toby"), is("Hello Toby"));
			assertThat(proxciedHello.sayHi("Toby"), is("Hi Toby"));
			assertThat(proxciedHello.sayThankYou("Toby"), is("Thank You Toby"));
		}
	}
	
	@Test
	public void proxyFactoryBean() {
		ProxyFactoryBean pfBean = new ProxyFactoryBean();
		pfBean.setTarget(new HelloTarget());
		
		NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
		pointcut.setMappedName("sayH*");
		
		pfBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, new UppercaseAdvice()));
		
		Hello proxiedHello = (Hello) pfBean.getObject();
		
		assertThat(proxiedHello.sayHello("Toby"), is("HELLO TOBY"));
		assertThat(proxiedHello.sayHi("Toby"), is("HI TOBY"));
		assertThat(proxiedHello.sayThankYou("Toby"), is("Thank You Toby"));
	}
	
	static class UppercaseAdvice implements MethodInterceptor {
		public Object invoke(MethodInvocation invocation) throws Throwable {
			String ret = (String)invocation.proceed();
			return ret.toUpperCase();
		}
	}
	
	static interface Hello {
		String sayHello(String name);
		String sayHi(String name);
		String sayThankYou(String name);
	}
	
	static class HelloTarget implements Hello {
		@Override
		public String sayHello(String name) {
			return "Hello " + name;
		}
		@Override
		public String sayHi(String name) {
			return "Hi " + name;
		}
		@Override
		public String sayThankYou(String name) {
			return "Thank You " + name;
		}
}
}
