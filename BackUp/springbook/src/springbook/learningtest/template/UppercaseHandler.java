package springbook.learningtest.template;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UppercaseHandler implements InvocationHandler {
	Object target;
	
	//다이내믹 프록시로부터 전달받은 요청을 다시 타깃 오브젝트에 위임해야 하기 때문에 타깃 오브젝트를 주입받아둔다.
	//어떤 종류의 인터페이스를 구현한 타깃에도 적용 가능하도록 Object 타입으로 수정
	public UppercaseHandler(Object target) {
		this.target = target;
	}
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
		Object ret = method.invoke(target, args);

		//일단 Obj로 받았기 때문에 메소드의 정보를 가지고 부가기능을 선별해서 적용할 수 있다.
		if(ret instanceof String && method.getName().startsWith("say")) {
			return ((String)ret).toUpperCase();
		}
		else {
			return ret;
		}
	}
	
}
