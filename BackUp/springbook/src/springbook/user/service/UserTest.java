package springbook.user.service;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import springbook.user.domain.Levelu;
import springbook.user.domain.User;
import sun.util.logging.PlatformLogger.Level;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:WebContent/WEB-INF/test-applicationContext.xml"})
public class UserTest {
	User user;
	
	@Before
	public void setUp() {
		user = new User();
	}
	
	@Test
	public void upgradeLevel() {
		Levelu[] levels = Levelu.values();
		
		for(Levelu level : levels) {
			if(level.nextLevel() == null) continue;
			user.setLevelu(level);
			user.upgradeLevel();
			assertThat(user.getLevelu(), is(level.nextLevel()));
		}
	}
	
	@Test(expected = IllegalStateException.class)
	public void cannotUpgradeLevel() {
		Levelu[] levels = Levelu.values();
		for(Levelu level : levels) {
			if(level.nextLevel() != null) continue;
			user.setLevelu(level);
			user.upgradeLevel();
		}
	}

}
