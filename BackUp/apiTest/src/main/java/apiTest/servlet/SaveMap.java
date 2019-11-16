package apiTest.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import apiTest.VO.JsonDTO;
import apiTest.VO.MapDTO;

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
		//String latlng = "new kakao.maps.LatLng("+x+", "+y+")";
		
		System.out.println("x :" + x);
		System.out.println("y :" + y);
		System.out.println("title :" + title);
		//System.out.println("latlng :" + latlng);
		
		//JsonDTO jvo = new JsonDTO(x, y, title, latlng);
		
		JSONObject jobj = new JSONObject();
		jobj.put("title", title);
		jobj.put("x", x);
		jobj.put("y", y);
		
		JSONArray jarr = new JSONArray();
		jarr.put(jobj);
		
		
        String json = jarr.toString();
        
        request.setAttribute("json", json);
        request.getRequestDispatcher("Map2.jsp").forward(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
