package apiTest.json.util;

import org.json.simple.JSONObject;

public class MakingJSON implements JSONMakeable{
	@Override
	public JSONObject addJson(String key, String value) {
		JSONObject jsonObj = new JSONObject();
		
		jsonObj.put(key, value);
		
		return jsonObj;
	}
	
	@Override
	public void JsonConsole(JSONObject jsonObj) {
		System.out.println(jsonObj.toString());
	}
}
