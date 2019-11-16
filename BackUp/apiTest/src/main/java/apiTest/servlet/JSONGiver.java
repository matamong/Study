package apiTest.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import apiTest.VO.MapDTO;



@WebServlet("/JSONGiver")
public class JSONGiver extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;");
        resp.setCharacterEncoding("utf-8");
        
        PrintWriter out = resp.getWriter();

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("test", 000);
        jsonObj.put("test2", "String");
        String json = jsonObj.toString();
        System.out.println(json);
        
//        out.print(json);
//        out.flush();
//        out.close();
        
//        req.setAttribute("json", json);
        //req.getRequestDispatcher("JSONTest.jsp").forward(req, resp);
        
     // Assuming your json object is **jsonObject**, perform the following, it will return your json object  
        
        MapDTO dto = null;
        ArrayList<MapDTO> list = new ArrayList<MapDTO>();
        for(int i= 0; i<2; i++) {
        	dto = new MapDTO("test1", i, i, "test1", "test1", "test1");
        	list.add(dto);
        }
        
        System.out.println(list);;
        req.setAttribute("list", list);
        req.getRequestDispatcher("TakeJsonTest.jsp").forward(req, resp);
	}

}
