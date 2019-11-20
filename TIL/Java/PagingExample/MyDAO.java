package kr.ac.green.dbcp.dao;

import java.io.*;
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

import kr.ac.green.dbcp.dao.*;

public class MyDAO {
	private DataSource ds;
	
	private static final MyDAO INSTANCE = new MyDAO();
	
	private MyDAO() {
		try {
			Context init = new InitialContext(); 
			ds = (DataSource)init.lookup("java:comp/env/jdbc/mysqlDB");
		} catch (NamingException e) {			
			e.printStackTrace();
		}
	}
	
	public static MyDAO getInstance() {
		return INSTANCE;
	}
	
	public Connection connect() {
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
		return con;
	}
	
	public int insertMember(String id, String pw, String name, String email, String phone ) {
		int result = 0;
		Connection con = null;
		PreparedStatement pStmt = null;	
		try {
			 con = ds.getConnection();
			 pStmt = con.prepareStatement(
	 "INSERT INTO mymember VALUE(NULL,?,?,?,?,?)");
			 pStmt.setString(1, id);
			 pStmt.setString(2, pw);
			 pStmt.setString(3, name);
			 pStmt.setString(4, email);
			 pStmt.setString(5, phone);
			 result = pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {pStmt.close();} catch (Exception e) {}
			try {con.close();} catch(Exception e) {}
		}	
		return result;
	}
	
	public Vector<MyDTO> getMembers() {
		
		String sql = "SELECT * FROM mymember";
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		Vector<MyDTO> v=null;
		
		try {
			con = connect();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			v = new Vector<MyDTO>();
			
			while(rs.next()) {
				MyDTO dto = new MyDTO();
				dto.setIdx(rs.getInt("idx"));
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setEmail(rs.getString("email"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				v.add(dto);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}
		return v;
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public int getCount() {
		String sql = "SELECT count(*) totalCount FROM item";
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		int count = 0;
		try {
			con = connect();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				count = rs.getInt("totalCount");
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}
		return count;
	}
	
	
	public int writeImg(String imgName) {
		
		File file = new File(imgName);
        FileInputStream input=null;
		try {
			input = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result=0;
		
		try {
			
			con = ds.getConnection();
			
			sql = "insert into tblimg (img) values (?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setBinaryStream(1, input);			
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
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
	
	public String readImg(String idx) throws IOException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String result=null; 
		try {
			con = ds.getConnection();
			sql = "select img from tblimg where idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(idx));
			rs = pstmt.executeQuery();
			
			
			File file = new File("./this.png");
            FileOutputStream output = new FileOutputStream(file);            
            
            if (rs.next()) {
                InputStream input = rs.getBinaryStream(1);
                byte[] buffer = new byte[1024];
                while (input.read(buffer) > 0) {
                    output.write(buffer);
                }
                result=file.getAbsolutePath();	
            }
		} catch (Exception e) {
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











