package com.book.controler.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.dao.ProductDAO;
import com.book.dto.ProductVO;

public class ProductDetailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/product/product_detail.jsp";
		String pseq = request.getParameter("pseq").trim();
		/*pseq는 int이지만 request.getParameter("pseq")는 string type임. 
		 int로 형변환을 하거나 String 변수에 저장하거나 */
		ProductDAO pDao = ProductDAO.getInstance();
		ProductVO pVo = pDao.getProduct(pseq);
		request.setAttribute("pVo", pVo);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
