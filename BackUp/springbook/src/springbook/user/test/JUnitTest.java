package springbook.user.test;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;

import java.util.HashSet;
import java.util.Set;

public class JUnitTest {
	static Set<JUnitTest> testObjects = new HashSet<JUnitTest>();
	
	@Test
	public void test1() {
		assertThat(testObjects, not(sameInstance(this)));
		testObjects.add(this);
	}
	
	@Test
	public void test2() {
		assertThat(testObjects, not(sameInstance(this)));
		testObjects.add(this);
	}
	
	@Test
	public void test3() {
		assertThat(testObjects, not(sameInstance(this)));
		testObjects.add(this);
	}
}
