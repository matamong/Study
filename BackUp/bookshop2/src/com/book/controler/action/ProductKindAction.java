package com.book.controler.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.dao.ProductDAO;
import com.book.dto.ProductVO;

public class ProductKindAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String kind = request.getParameter("kind").trim();
		ProductDAO productDAO= ProductDAO.getInstance();
		ArrayList<ProductVO> productKindList = productDAO.listKindProduct(kind);
		request.setAttribute("productKindList", productKindList);
		
		String url="/product/productKind.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
		
		
	}

}
