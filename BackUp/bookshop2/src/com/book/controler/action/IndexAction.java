package com.book.controler.action;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.dao.ProductDAO;
import com.book.dto.ProductVO;

public class IndexAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="index.jsp";
		//DB로 부터 상품정보를 가져오는 비지니스 로직
		ProductDAO pDao= ProductDAO.getInstance();
		ArrayList<ProductVO> newProductList = pDao.listNewProduct();
		ArrayList<ProductVO> bestProductList = pDao.listBestProduct();

		request.setAttribute("newProductList", newProductList);
		request.setAttribute("bestProductList", bestProductList);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
