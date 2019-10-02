package com.book.controler.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.book.dao.CartDAO;
import com.book.dto.CartVO;
import com.book.dto.MemberVO;

public class CartListAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="/mypage/cartList.jsp";
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		if(loginUser==null) {
			 url="bookShop?command=login_form";
			 
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}else {
			System.out.println("로그인완료");
			
			CartDAO cartDAO = CartDAO.getInstance();
			ArrayList<CartVO> cartList = cartDAO.listCart(loginUser.getId());
			int totalPrice = 0;
			for(CartVO cartVO : cartList) {
				 totalPrice += cartVO.getPrice2()* cartVO.getQuantity();
			
				request.setAttribute("cartList", cartList);
				request.setAttribute("totalPrice", totalPrice);
			}
			  RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			  dispatcher.forward(request, response);
		}
	}
}
