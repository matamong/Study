package apiTest.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import apiTest.VO.JsonVO;

/**
 * Servlet implementation class SaveMap
 */
@WebServlet("/SaveMap.do")
public class SaveMap extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveMap() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String x = request.getParameter("x");
		String y = request.getParameter("y");
		String title = request.getParameter("title");
		String latlng = "new kakao.maps.LatLng("+x+", "+y+")";
		
		System.out.println("x :" + x);
		System.out.println("y :" + y);
		System.out.println("title :" + title);
		System.out.println("latlng :" + latlng);
		
		JsonVO jvo = new JsonVO(x, y, title);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
