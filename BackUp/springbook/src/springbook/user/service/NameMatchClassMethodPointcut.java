package springbook.user.service;

import org.springframework.util.PatternMatchUtils;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.NameMatchMethodPointcut;

public class NameMatchClassMethodPointcut extends NameMatchMethodPointcut{
	
	//모든 클래스를 다 허용하던 디폴트 클래스 필터를 프로퍼티로 받은 클래스 이름을 이용해서 필터를 만들어 덮어씌운다.
	public void setMappedClassName(String mappedClassName) {
		this.setClassFilter(new SimpleClassFilter(mappedClassName));
	}
	
	static class SimpleClassFilter implements ClassFilter {
		String mappedName;
		
		private SimpleClassFilter(String mappedName) {
			this.mappedName = mappedName;
		}
		
		public boolean matches(Class<?> clazz) {
			//*이 들어간 문자열 비교를 지원하는 스프링의 유틸리티 메소드
			return PatternMatchUtils.simpleMatch(mappedName, clazz.getSimpleName());
		}
	}
}
