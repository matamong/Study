package kr.ac.green.dbcp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BoardDAO {
	private DataSource ds;
	
	private static final BoardDAO INSTANCE = new BoardDAO();
	
	private BoardDAO() {
		try {
			Context init = new InitialContext(); 
			ds = (DataSource)init.lookup("java:comp/env/jdbc/mysqlDB");
		} catch (NamingException e) {			
			e.printStackTrace();
		}
	}
	
	public static BoardDAO getInstance() {
		return INSTANCE;
	}	

	
	public int insertBoard(String pw, String name, String email, String homepage, String subject, String memo, String file1, String file2) {
		int result = 0;	
		Connection con=null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(
	"insert into board1 values(null,?,?,?,?,?,?,?,?,now())");
			pstmt.setString(1,pw);
			pstmt.setString(2,name);
			pstmt.setString(3,email);
			pstmt.setString(4,homepage);
			pstmt.setString(5,subject);
			pstmt.setString(6,memo);
			pstmt.setString(7,file1);
			pstmt.setString(8,file2);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	public Vector<BoardDTO> getList(int start, int cnt){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Vector<BoardDTO> vec = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(
					"select * from board1 limit ?, ?");
			pstmt.setInt(1, start);
			pstmt.setInt(2, cnt);
			rs = pstmt.executeQuery();
			vec = new Vector<BoardDTO>();
			
			while(rs.next()) {
				BoardDTO dto= new BoardDTO();
				dto.setIdx(rs.getInt(1));
				dto.setPw(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setEmail(rs.getString(4));
				dto.setHomepage(rs.getString(5));
				dto.setSubject(rs.getString(6));
				dto.setMemo(rs.getString(7));
				dto.setFile1(rs.getString(8));
				dto.setFile2(rs.getString(9));
				dto.setReday(rs.getString(10));
				vec.add(dto);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return vec;
	}
	
	public BoardDTO getView(String idx){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDTO dto = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(
					"select * from board1 where idx=?");
			pstmt.setInt(1, Integer.parseInt(idx));			
			rs = pstmt.executeQuery();			
			if(rs.next()) {
				dto = new BoardDTO();
				dto.setIdx(rs.getInt(1));
				dto.setPw(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setEmail(rs.getString(4));
				dto.setHomepage(rs.getString(5));
				dto.setSubject(rs.getString(6));
				dto.setMemo(rs.getString(7));
				dto.setFile1(rs.getString(8));
				dto.setFile2(rs.getString(9));
				dto.setReday(rs.getString(10));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return dto;
	}
	
	public int deleteView(String idx) {
		int result =0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(
					"delete from board1 where idx=?");
			pstmt.setInt(1, Integer.parseInt(idx));
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return result;
	}
	
	public int updateView(String idx, String pw, String name, String email, String homepage, String subject, String memo, String file1, String file2) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(
					"update board1 set"
					+ " pw=?,"
					+ " email=?,"
					+ " homepage=?,"
					+ " subject=?,"
					+ " memo=?,"
					+ " file1=?,"
					+ " file2=?,"
					+ " reday = now()"
					+ " where idx=?"
					);
			pstmt.setString(1, pw);
			pstmt.setString(2, email);
			pstmt.setString(3, homepage);
			pstmt.setString(4, subject);
			pstmt.setString(5, memo);
			pstmt.setString(6, file1);
			pstmt.setString(7, file2);
			pstmt.setInt(8, Integer.parseInt(idx));
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		return result;
	}
	
	public float getCount() {
		Connection con = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		float result = 0;// 전체갯수
		
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(
					"select count(*) from board1");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
				
		
		return result;
	}
	
}














