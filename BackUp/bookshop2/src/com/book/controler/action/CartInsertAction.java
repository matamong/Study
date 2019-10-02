package com.book.controler.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.book.dao.CartDAO;
import com.book.dto.CartVO;
import com.book.dto.MemberVO;

public class CartInsertAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="bookShop?command=cart_list";
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		if(loginUser==null) {
			url="bookShop?command=login_form";
		}else {
			CartVO cartVO = new CartVO();
			cartVO.setId(loginUser.getId());
			cartVO.setPseq(Integer.parseInt(request.getParameter("pseq")));
			cartVO.setQuantity(Integer.parseInt(request.getParameter("quantity")));
			CartDAO cartDAO = CartDAO.getInstance();
			int n = cartDAO.insertCart(cartVO);
			if(n>0) {System.out.println("카트넣기성공");} else{System.out.println("카트넣기실패");}
		}
		response.sendRedirect(url);
		
	}

}
