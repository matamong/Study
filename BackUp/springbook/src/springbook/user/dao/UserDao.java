package springbook.user.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import springbook.user.domain.User;

public class UserDao {
	//private JdbcContext jdbcContext;
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	private Connection conn;
	private User user;


	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);

		this.dataSource = dataSource; // 이거는 아직 주입 적용 안시킨거 때문에 남겨둔거.
	}

	
	public void add(final User user) {
		this.jdbcTemplate.update("insert into users(id, name, password) values(?,?,?)",
				user.getId(), user.getName(), user.getPassword());
	}

	public void deleteAll() {
		this.jdbcTemplate.update("delete from users");
	}

	public int getCount() {
		return this.jdbcTemplate.queryForInt("select count(*) from users");
	}
	
	
	public User get(String id) {
		return this.jdbcTemplate.queryForObject("select * from users where id=?", new Object[] {id},

				new RowMapper<User>() {

					public User mapRow(ResultSet rs, int rowNum) throws SQLException{
							User user = new User();
							user.setId(rs.getString("id"));
							user.setName(rs.getString("name"));
							user.setPassword(rs.getString("password"));
							return user;
					}
		});
	}
	
	public List<User> getAll() {
		return this.jdbcTemplate.query(
				"select * from users order by id asc",
				new RowMapper<User>() {
					public User mapRow(ResultSet rs, int rowNum)throws SQLException{
						User user = new User();
						user.setId(rs.getString("id"));
						user.setName(rs.getString("name"));
						user.setPassword(rs.getString("password"));
						return user;
					}
				});
	}
}

