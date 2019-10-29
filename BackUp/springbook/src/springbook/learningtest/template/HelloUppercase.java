package springbook.learningtest.template;

import org.junit.Test;

//전형적인 프록시 클래스
public class HelloUppercase implements Hello{
	
	Hello hello;
	
	
	public HelloUppercase(Hello hello) {
		this.hello = hello;
	}
	
	
	@Override
	public String sayHello(String name) {
		//위임과 부가기능 적용
		return hello.sayHello(name).toUpperCase();
	}
	
	@Override
	public String sayHi(String name) {
		return hello.sayHi(name).toUpperCase();
	}
	
	@Override
	public String sayThankYou(String name) {
		return hello.sayThankYou(name).toUpperCase();
	}
	
}
