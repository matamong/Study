package springbook.learningtest.spring.pointcut;

public class Target implements TargetInterface{
	
	//interface 구현한 메소드
	public void hello() {}
	public void hello(String a) {}
	public int minus(int a, int b) throws RuntimeException { return 0; }
	public int plus(int a, int b) { return 0; }
	
	//새로 쓴 메소드
	public void method() {}
	
}
