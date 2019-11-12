package apiTest.servlet;

import java.io.IOException;

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
        resp.setContentType("application/json;charset=utf-8");
        
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("test", 000);
        String json = jsonObj.toJSONString();
        req.setAttribute("json", json);
        req.getRequestDispatcher("JSONTest.jsp").forward(req, resp);
        
	}

}
