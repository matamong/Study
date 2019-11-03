package apiTest.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import apiTest.json.util.test.JSONMaker;

@WebServlet("/PushToJsp")
public class PushToJSP extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		
		JSONObject jsonObj = JSONMaker.makeJSON();
		
		JSONObject respJson = jsonObj.getJSONObject("response");
		JSONObject bodyJson = respJson.getJSONObject("body");
		JSONObject itemsJson = bodyJson.getJSONObject("items");
		
		JSONArray itemArr = itemsJson.getJSONArray("item");
		
		req.setAttribute("itemArr", itemArr);
		req.getRequestDispatcher("test.jsp").forward(req, resp);
		
	}
}
