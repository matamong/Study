package springbook.user.service;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:WebContent/WEB-INF/applicationContext.xml"})
public class UserServiceTest {
	
	@Autowired
	UserService userService;
	
	@Test //UserService의 빈이 생성되고 여기에 제대로 주입됐는지 확인 용
	public void bean() {
		assertThat(this.userService, is(notNullValue()));
	}
}
