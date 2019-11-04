package apiTest.kakaoMap;

import java.net.URLEncoder;
import java.util.HashMap;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

public class RequestToKamap {
	public void getConnect() {
		String APIKey = "";
		
		HashMap<String, Object> map = new HashMap(); //결과 담을 map
		
		try {
			String apiURL = "" + URLEncoder.encode("address", "UTF-8");
			HttpResponse<JsonNode> response = Unirest.get(apiURL).header("Authorization", APIKey).asJson();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
