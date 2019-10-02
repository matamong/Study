package com.book.controler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.controler.action.Action;

@WebServlet("/bookShop")
public class BookShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BookShopServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command"); //ex) index, login_form, join_form...
		System.out.println("BookShopServlet에서 요청: " + command);
		ActionFactory actionFactory = ActionFactory.getInstance();
		Action action = actionFactory.getAction(command);
		if(action != null) {
			action.execute(request,response);
		}
	}
}
