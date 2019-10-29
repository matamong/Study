package springbook.learningtest.template;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class HelloTarget implements Hello {
	
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
	
	@Test
	public void simpleProxy() {
		
		//타깃은 항상 인터페이스를 통해서 접근하는 습관을 들이자.
		Hello hello = new HelloTarget();
		
		assertThat(hello.sayHello("Toby"), is("Hello Toby"));
		assertThat(hello.sayHi("Toby"), is("Hi Toby"));
		assertThat(hello.sayThankYou("Toby"), is("Thank You Toby"));
		
		Hello proxedHello = new HelloUppercase(new HelloTarget());
		assertThat(proxedHello.sayHello("Toby"), is("HELLO TOBY"));
		assertThat(proxedHello.sayHi("Toby"), is("HI TOBY"));
		assertThat(proxedHello.sayThankYou("Toby"), is("THANK YOU TOBY"));
	}
}
