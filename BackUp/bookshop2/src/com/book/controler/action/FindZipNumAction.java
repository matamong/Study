package com.book.controler.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.dao.AddressDAO;
import com.book.dto.AddressVO;

public class FindZipNumAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="/member/findZipNum.jsp";
		//주소 DAO처리
	
		String dong=request.getParameter("dong");
		System.out.println("입력한 주소 : "+dong);

		if((dong != null && dong.trim().equals(""))==false) {
			AddressDAO addressDAO = AddressDAO.getInstance();
			ArrayList<AddressVO> list = AddressDAO.selectAddressByDong(dong);
			request.setAttribute("list", list);
		}else{
			System.out.println("FindZipNumAction.java : 없는 주소입니다.");
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}
