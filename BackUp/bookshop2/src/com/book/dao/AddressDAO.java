package com.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.book.dto.AddressVO;

import util.DBManager;

public class AddressDAO {	
	private AddressDAO() {}
	private static AddressDAO instance = new AddressDAO();
	public static AddressDAO getInstance() {
		return instance;
		}

	public static ArrayList<AddressVO> selectAddressByDong(String dong) {
	
		String sql = "SELECT * FROM address_book WHERE dong Like '%' || ? || '%'"; /* || :  +처럼 연결시켜줌  */
		ArrayList<AddressVO> list = new ArrayList<AddressVO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, dong);
			rs = ps.executeQuery();
			while (rs.next()) { 
				AddressVO aVo = new AddressVO();
				aVo.setzipNum(rs.getString("zip_num"));
				aVo.setSido(rs.getString("sido"));
				aVo.setGugun(rs.getString("gugun"));
				aVo.setDong(rs.getString("dong"));		
				aVo.setzipCode(rs.getString("zip_Code"));		
				aVo.setBunji(rs.getString("bunji"));		
				list.add(aVo);
			}
		} catch (Exception e) {
			System.out.println("findZip예외발생 : "+e);
		} finally {
			DBManager.close(con, ps, rs);
		}
	return list;
	}
}
