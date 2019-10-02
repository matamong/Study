package com.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.book.dto.CartVO;

import util.DBManager;

public class CartDAO {
	private CartDAO() {}
	private static CartDAO instance = new CartDAO();
	public static CartDAO getInstance() {
		return instance;
	}
	
	public int insertCart(CartVO cartVO) {
		String sql="INSERT INTO cart_book(cseq, id, pseq, quantity) values(cart_book_seq.nextval,?,?,?)";
		int result = -1;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, cartVO.getId());
			ps.setInt(2, cartVO.getPseq());
			ps.setInt(3, cartVO.getQuantity());
			result = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("insertCart예외발생 : "+e);
		} finally {
			DBManager.close(con, ps);
		}
		return result;
	}
		
	public ArrayList<CartVO> listCart(String userid){// 여러개의 객체:Arraylist 한개의 객체:DTO
		String sql = "SELECT * FROM cart_book_view WHERE id=? ORDER BY cseq DESC";
		ArrayList<CartVO> cartList = new ArrayList<CartVO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userid);
			rs = ps.executeQuery();
			while (rs.next()) { 
				CartVO cVo = new CartVO();
				/*
				cVo.setCseq(rs.getInt("cseq"));
				cVo.setId(rs.getString("id"));
				cVo.setPseq(rs.getInt("pseq"));
				cVo.setMname(rs.getString("mname"));
				cVo.setQuantity(rs.getInt("quantity"));
				cVo.setIndate(rs.getTimestamp("indate"));
				cVo.setPrice2(rs.getInt("price2"));
				 */
				cVo.setCseq(rs.getInt(1));
				cVo.setId(rs.getString(2));
				cVo.setPseq(rs.getInt(3));
				cVo.setMname(rs.getString(4));
				cVo.setPname(rs.getString(5));
				cVo.setQuantity(rs.getInt(6));
				cVo.setIndate(rs.getTimestamp(7));
				cVo.setPrice2(rs.getInt(8));
				cartList.add(cVo);
			}// while문 끝
		} catch (Exception e) {
			System.out.println("listCart 예외발생 : "+e);
		} finally {
			DBManager.close(con, ps, rs);
		}
		return cartList;
	}
	public void deleteCart(int csep) {
		String sql = "DELETE cart_book WHERE csep=?";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, csep);
			/* result = ps.executeUpdate(); */
		} catch (Exception e) {
			System.out.println("deleteCart예외발생 : "+e);
		} finally {
			DBManager.close(con, ps);
		}
	}
		
	
}
