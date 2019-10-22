package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;

import springbook.user.domain.User;

public class UserDao {
	private DataSource dataSource;
	private Connection conn;
	private User user;

	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}

	public void add(User user) throws ClassNotFoundException, SQLException {
		Connection conn = dataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement("insert into users(id,name,password) values(?,?,?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());

		ps.executeUpdate();

		ps.close();
		conn.close();
	}

	public User get(String id) throws ClassNotFoundException, SQLException {
		this.conn = dataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from users where id = ?");

		ps.setString(1, id);

		ResultSet rs = ps.executeQuery();

		User user = null;

		if(rs.next()) {
			user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
		}

		rs.close();
		ps.close();
		conn.close();

		if(user == null) throw new EmptyResultDataAccessException(1);

		return user;
	}

	public void deleteAll() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("delete from users");
			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("SQL 예외 발생: " + e);
			throw e;
		}finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e2) {
					System.out.println("SQL 예외 발생: " + e2);
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e2) {
					System.out.println("SQL 예외 발생! : " + e2);
				}
			}
		}

	}

	public int getCount() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("select count(*) from users");

			rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			System.out.println("SQL 예외 발생 " + e);
			throw e;
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e2) {
					System.out.println("SQL예외발생: " + e2);
				}
			}
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e2) {
					System.out.println("SQL 예외 발생: " + e2);
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e2) {
					System.out.println("SQL 예외 발생! : " + e2);
				}
			}
		}
	}
}

