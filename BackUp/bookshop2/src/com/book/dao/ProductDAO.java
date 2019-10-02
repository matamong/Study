package com.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.book.dto.ProductVO;

import util.DBManager;

public class ProductDAO {
	private ProductDAO() {}
	private static ProductDAO instance = new ProductDAO();
	public static ProductDAO getInstance() {
		return instance;
	}
	public ArrayList<ProductVO> listNewProduct() { //객체를 여러개 생성해야하므로 크기가 고정된 배열 대신 가변형 리스트를 사용.
		String sql = "SELECT * FROM new_pro_view";
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) { // 이동은 행(로우) 단위로
				ProductVO pVo = new ProductVO();
				pVo.setPseq(rs.getInt("pseq"));
				pVo.setName(rs.getString("name"));
				pVo.setPrice2(rs.getInt("price2"));
				pVo.setImage(rs.getString("image"));		
				list.add(pVo);
			}// while문 끝
		} catch (Exception e) {
			System.out.println("예외발생 : "+e);
		} finally {
			DBManager.close(con, ps, rs);
		}
		return list;
	}
	
	
	 public ArrayList<ProductVO> listBestProduct() { 
		String sql = "SELECT * FROM best_pro_view";
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) { // 이동은 행(로우) 단위로
				ProductVO pVo = new ProductVO();
				pVo.setPseq(rs.getInt("pseq"));
				pVo.setName(rs.getString("name"));
				pVo.setPrice2(rs.getInt("price2"));
				pVo.setImage(rs.getString("image"));
				list.add(pVo);
			}// while문 끝
		} catch (Exception e) {
			System.out.println("예외발생 : "+e);
		} finally {
			DBManager.close(con, ps, rs);
		}
		return list;
	}
	 
	 public ProductVO getProduct(String pseq) {  //자료가 1개이므로 arraylist필요없음
		String sql = "SELECT * FROM product_book WHERE pseq=?";
		ProductVO pVo = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
			try {
				con = DBManager.getConnection();
				ps = con.prepareStatement(sql);
				ps.setString(1, pseq);
				rs = ps.executeQuery();
				if (rs.next()) {
					pVo = new ProductVO();  //검색 결과가 있다면 객체생성
					pVo.setPseq(rs.getInt("pseq"));
					pVo.setName(rs.getString("name"));
					pVo.setKind(rs.getString("kind"));					
					pVo.setPrice1(rs.getInt("price1"));
					pVo.setPrice2(rs.getInt("price2"));
					pVo.setPrice3(rs.getInt("price3"));
					pVo.setContent(rs.getString("content"));
					pVo.setImage(rs.getString("image"));
					pVo.setUseyn(rs.getString("useyn"));
					pVo.setBestyn(rs.getString("bestyn"));
					pVo.setIndate(rs.getTimestamp("indate"));					
				}
			} catch (Exception e) {
				System.out.println("예외발생" + e);
			} finally {
				DBManager.close(con, ps, rs);
			}
			return pVo;
		}
	 public ArrayList<ProductVO> listKindProduct(String kind){
		 String sql = "SELECT * FROM product_book WHERE kind=?";
		 ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		 Connection con = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
			try {
				con = DBManager.getConnection();
				ps = con.prepareStatement(sql);
				ps.setString(1, kind);
				rs = ps.executeQuery();
				if (rs.next()) {
					ProductVO pVo = new ProductVO();  
					pVo.setPseq(rs.getInt("pseq"));
					pVo.setName(rs.getString("name"));
					pVo.setPrice2(rs.getInt("price2"));
					pVo.setImage(rs.getString("image"));
					list.add(pVo);
				}
			} catch (Exception e) {
				System.out.println("예외발생" + e);
			} finally {
				DBManager.close(con, ps, rs);
			}
		 return list;
	 }
	 
}
