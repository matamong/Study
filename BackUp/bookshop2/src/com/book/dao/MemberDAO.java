package com.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.book.dto.MemberVO;
import com.book.dto.ProductVO;

import util.DBManager;

public class MemberDAO {
	private MemberDAO() {}
	private static MemberDAO instance = new MemberDAO();
	public static MemberDAO getInstance() {
		return instance;
	}
	
	public int confirmID(String userId) {
		int result=-1;
		String sql="SELECT * FROM member_book WHERE id=?";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				result=1;
			}else {
				result=-1;
			}
		} catch (Exception e) {
			System.out.println("confirmID예외발생 : "+e);
		} finally {
			DBManager.close(con, ps, rs);
		}
		return result;
	}
	public int insertMember(MemberVO memberVO) {
		int result=0;
		String sql="INSERT INTO member_book(id, pwd, name, zip_num, ";
		sql+="address, phone) values(?,?,?,?,?,?)";
		
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, memberVO.getId());
			ps.setString(2, memberVO.getPwd());
			ps.setString(3, memberVO.getName());
			ps.setString(4, memberVO.getZipNum());
			ps.setString(5, memberVO.getAddress());
			ps.setString(6, memberVO.getPhone());
			result = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("insertMember예외발생 : "+e);
		} finally {
			DBManager.close(con, ps);
		}
		return result;
	}
	public MemberVO getMember(String userId) {
		String sql="SELECT * FROM member_book WHERE id=?";
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			rs = ps.executeQuery();
			
			if (rs.next()) {
			memberVO= new MemberVO();
			memberVO.setId(rs.getString("id"));
			memberVO.setPwd(rs.getString("pwd"));
			memberVO.setName(rs.getString("name"));
			memberVO.setEmail(rs.getString("email"));
			memberVO.setZipNum(rs.getString("zip_Num"));
			memberVO.setAddress(rs.getString("address"));
			memberVO.setPhone(rs.getString("phone"));
			memberVO.setUseyn(rs.getString("useyn"));
			memberVO.setIndate(rs.getTimestamp("indate"));
			}
		} catch (Exception e) {
			System.out.println("getMember예외발생 : "+e);
		} finally {
			DBManager.close(con, ps, rs);
		}
		return memberVO;
	}

	
	
	
	
	
	
	
	
	
	
}
