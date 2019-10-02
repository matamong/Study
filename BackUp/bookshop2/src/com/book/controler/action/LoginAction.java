package com.book.controler.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.book.dao.MemberDAO;
import com.book.dto.MemberVO;

public class LoginAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="/member/login_fail.jsp";
		HttpSession session = request.getSession();
		
		String id=request.getParameter("id");
		String pwd= request.getParameter("pwd");
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		MemberVO memberVO = memberDAO.getMember(id);
		if(memberVO != null) { //DB에 있는 비번과 입력받은 비번을 비교
			if(memberVO.getPwd().equals(pwd)) {
				session.removeAttribute("id");
				session.setAttribute("loginUser", memberVO);
				url="bookShop?command=index";
			}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
