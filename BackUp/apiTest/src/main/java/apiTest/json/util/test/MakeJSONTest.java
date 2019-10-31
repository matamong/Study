package apiTest.json.util.test;

import org.json.simple.JSONObject;

import apiTest.json.util.MakingJSON;

public class MakeJSONTest {
	public static void main(String[] args) {
		MakingJSON makeJson = new MakingJSON();
		
		JSONObject jsonObj = makeJson.addJson("id", "test");
		
		makeJson.JsonConsole(jsonObj);
	}
}
