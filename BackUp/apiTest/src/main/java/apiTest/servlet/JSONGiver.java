package apiTest.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;



@WebServlet("/JSONGiver")
public class JSONGiver extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;");
        resp.setCharacterEncoding("utf-8");
        
        PrintWriter out = resp.getWriter();

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("test", 000);
        String json = jsonObj.toString();
        System.out.println(json);
        
//        out.print(json);
//        out.flush();
//        out.close();
        
        req.setAttribute("json", json);
        req.getRequestDispatcher("JSONTest.jsp").forward(req, resp);
        
     // Assuming your json object is **jsonObject**, perform the following, it will return your json object  
        
	}

}
