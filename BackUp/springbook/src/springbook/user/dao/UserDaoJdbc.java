package springbook.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import springbook.user.domain.Levelu;
import springbook.user.domain.User;

public class UserDaoJdbc implements UserDao{
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<User> userMapper =
			new RowMapper<User>() {
				public User mapRow(ResultSet rs, int rowNum) throws SQLException{
					User user = new User();
					user.setId(rs.getString("id"));
					user.setName(rs.getString("name"));
					user.setPassword(rs.getString("password"));
					user.setLevelu(Levelu.valueOf(rs.getInt("levelu")));
					user.setLogin(rs.getInt("login"));
					user.setRecommend(rs.getInt("recommend"));
					user.setEmail(rs.getString("email"));
					return user;
				}
	};

	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	
	public void add( User user) {
		this.jdbcTemplate.update(
				"insert into users(id, name, password, levelu, login, recommend, email) values(?,?,?,?,?,?,?)",
				user.getId(), user.getName(), user.getPassword(), 
				user.getLevelu().intValue(), user.getLogin(), user.getRecommend(), user.getEmail());
	}

	public void deleteAll() {
		this.jdbcTemplate.update("delete from users");
	}

	public int getCount() {
		return this.jdbcTemplate.queryForInt("select count(*) from users");
	}
	
	
	public User get(String id) {
		return this.jdbcTemplate.queryForObject("select * from users where id=?", 
				new Object[] {id}, this.userMapper);
	}
	
	public List<User> getAll() {
		return this.jdbcTemplate.query(
				"select * from users order by id asc",this.userMapper);
	}


	@Override
	public void update(User user) {
		this.jdbcTemplate.update(
				"update users set name=?, password=?, levelu=?, login=?,recommend=?, email=? where id=?",
				user.getName(), user.getPassword(),
				user.getLevelu().intValue(), user.getLogin(),
				user.getRecommend(),user.getEmail(),user.getId());
	}
}

