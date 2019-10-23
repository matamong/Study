package springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

public class JdbcContext {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void executeSql(final String query) throws SQLException{
		workWithStatementStrategy(
				new StatementStrategy() {
					@Override
					public PreparedStatement makePreparedStatement(Connection conn) throws SQLException {
						return conn.prepareStatement(query);
					}
				});
	}

	public void workWithStatementStrategy(StatementStrategy stmt) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = dataSource.getConnection();
			ps = stmt.makePreparedStatement(conn);
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

}
