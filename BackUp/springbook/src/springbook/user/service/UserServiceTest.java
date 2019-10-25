package springbook.user.service;

import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import springbook.user.dao.UserDao;
import springbook.user.domain.Levelu;
import springbook.user.domain.User;

import static springbook.user.service.UserService.MIN_LOGCOUNT_FOR_SILVER;
import static springbook.user.service.UserService.MIN_RECCOMEND_FOR_GOLD;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:WebContent/WEB-INF/test-applicationContext.xml"})
public class UserServiceTest {
	
	@Autowired    //dao도 주입시켜줘야 dao를 쓸 수 있다!
	UserDao userDao;
	
	@Autowired
	UserService userService;

	List<User> users;
	
	@Test //UserService의 빈이 생성되고 여기에 제대로 주입됐는지 확인 용
	public void bean() {
		assertThat(this.userService, is(notNullValue()));
	}
	
	@Before
	public void setUp() {
		users = Arrays.asList(
				new User("girl1", "걸1", "p1", Levelu.BASIC, MIN_LOGCOUNT_FOR_SILVER-1,0),
				new User("girl2", "걸2", "p2", Levelu.BASIC, MIN_LOGCOUNT_FOR_SILVER, 0),
				new User("girl3", "걸3", "p3", Levelu.SILVER, MIN_RECCOMEND_FOR_GOLD-1, 29),
				new User("girl4", "걸4", "p4", Levelu.SILVER, MIN_RECCOMEND_FOR_GOLD, 30),
				new User("girl5", "걸5", "p5", Levelu.GOLD, 100, Integer.MAX_VALUE)
				);
		//테스트 값은 경계가 되는 값의 전후로 선택하는 것이 좋다.
	}
	
	@Test
	public void upgradeLevels() {
		userDao.deleteAll();
		for(User user: users) userDao.add(user);
		
		userService.upgradeLevels();
		
		checkLevelUpgraded(users.get(0), false);
		checkLevelUpgraded(users.get(1), true);
		checkLevelUpgraded(users.get(2), false);
		checkLevelUpgraded(users.get(3), true);
		checkLevelUpgraded(users.get(4), false);
	
	}
	
	@Test
	public void add() {
		userDao.deleteAll();
		
		User userWithLevel = users.get(4);
		User userWithoutLevel = users.get(0);
		userWithoutLevel.setLevelu(null);
		
		userService.add(userWithLevel);
		userService.add(userWithoutLevel);
		
		User userWithLevelRead = userDao.get(userWithLevel.getId());
		User userWithoutLevelRead = userDao.get(userWithoutLevel.getId());
				
		assertThat(userWithLevelRead.getLevelu(), is(userWithLevel.getLevelu()));
		assertThat(userWithoutLevel.getLevelu(), is(Levelu.BASIC));
	}
	
	
	private void checkLevelUpgraded(User user, boolean upgraded) {
		User userUpdate = userDao.get(user.getId());
		if(upgraded) {
			assertThat(userUpdate.getLevelu(), is(user.getLevelu().nextLevel()));
		}
		else {
			assertThat(userUpdate.getLevelu(), is(user.getLevelu()));
		}
	}
	
}
