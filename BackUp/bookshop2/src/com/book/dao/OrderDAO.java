package com.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.book.dto.CartVO;
import com.book.dto.OrderVO;

import util.DBManager;

public class OrderDAO {
	private OrderDAO() {}
	private static OrderDAO instance = new OrderDAO();
	public static OrderDAO getInstance() {
		return instance;
	}

	public int insertOrder(ArrayList<CartVO> cartList, String id) {
			String sql="SELECT max(oseq) FROM orders_book";
			int maxOseq=0;
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				con = DBManager.getConnection();
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				if(rs.next())
					maxOseq=rs.getInt(1);
				ps.close();
				
				
				String insertOrder = "INSERT INTO orders_book(oseq,id) VALUES"
						+ "(orders_book_seq.nextval, ?)";
				ps = con.prepareStatement(insertOrder);
				ps.setString(1,  id);
				ps.executeUpdate();
				
				for(CartVO cartVO:cartList) {
					insertOrderDetail(cartVO, maxOseq);
				}
			} catch (Exception e) {
				System.out.println("insertOrder 예외발생 : "+e);
			} finally {
				DBManager.close(con, ps);
			}
			return maxOseq;
	}
	
	public void insertOrderDetail(CartVO cartVO, int maxOseq) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBManager.getConnection();
			String insertOrderDetail = "INSERT INTO orders_book_detail(odseq, oseq, pseq, quantity)"
					+ "VALUES(orders_book_detail_seq.nextval,?,?,?)";
			ps = con.prepareStatement(insertOrderDetail);
			ps.setInt(1, maxOseq);
			ps.setInt(2, cartVO.getPseq());
			ps.setInt(3, cartVO.getQuantity());
			ps.executeUpdate();
			ps.close();
			
			String updateCartResult = "Update cart_book set result=2 WHERE cseq=?";
			ps = con.prepareStatement(updateCartResult);
			ps.setInt(1, cartVO.getCseq());
			ps.executeUpdate();
		} catch (Exception e) {
		} finally {
			DBManager.close(con, ps);
		}
	}
	//사용자가 자문내역 검색
	public ArrayList<OrderVO> listOrderById(String id, String result, int oseq){
		ArrayList<OrderVO> orderList = new ArrayList<OrderVO>();
		String sql="SELECT * FROM orders_book_view WHERE id=?"
				+ "AND result LIKE '%'||?||'%' AND oseq=?";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, result);
			ps.setInt(3, oseq);
			rs = ps.executeQuery();
			while(rs.next()) {
				OrderVO orderVO = new OrderVO();
				orderVO.setOdseq(rs.getInt("odseq"));
				orderVO.setOseq(rs.getInt("oseq"));
				orderVO.setId(rs.getString("id"));
				orderVO.setIndate(rs.getTimestamp("indate"));
				orderVO.setMname(rs.getString("mname"));
				orderVO.setZipNum(rs.getString("zip_Num"));
				orderVO.setAddress(rs.getString("address"));
				orderVO.setPseq(rs.getInt("pseq"));
				orderVO.setQuantity(rs.getInt("quantity"));
				orderVO.setPname(rs.getString("pname"));
				orderVO.setPrice2(rs.getInt("price2"));
				orderVO.setResult(rs.getString("result"));
				orderList.add(orderVO);
			}
		}catch (Exception e) {
			System.out.println("listOrderById 예외발생 : "+ e);
		} finally {
			DBManager.close(con, ps, rs);
		}
		return orderList;
	}

	//현재 진행 중인 주문 내역만 조회(관리자가 진행중인주문 처리 여부 1 또는 0)
	
	public ArrayList<Integer> selectSeqOrderId(String id){
		ArrayList<Integer> seqList = new ArrayList<Integer>();
		String sql="SELECT distinct oseq FROM orders_book_view "
				+ "WHERE id=? AND result ='1' order by oseq desc";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				OrderVO orderVO = new OrderVO();
				orderVO.setOseq(rs.getInt("oseq"));
				seqList.add(orderVO.getOseq());
			}
		}catch (Exception e) {
			System.out.println("selectSeqOrderId 예외발생 : "+ e);
		} finally {
			DBManager.close(con, ps, rs);
		}
		return seqList;
	}
	
	
	
	
	
	
	
	
	
	
}
