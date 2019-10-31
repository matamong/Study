package apiTest.json.util;

import org.json.simple.JSONObject;

public interface JSONMakeable {
	public JSONObject addJson(String key, String value);
	public void JsonConsole(JSONObject jsonObj);
}
