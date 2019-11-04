package apiTest.kakaoMap;

import java.net.URLEncoder;
import java.util.HashMap;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;

public class RequestToKamap {
	public void getConnect() {
		String APIKey = "";
		
		HashMap<String, Object> map = new HashMap(); //결과 담을 map
		
		//Unirest 라이브러리를 이용해서 Http 요청을 보내서 접속
		try {
			String apiURL = "" + URLEncoder.encode("address", "UTF-8");
			HttpResponse<JsonNode> response = Unirest.get(apiURL)				//해당 API URL에서
												.header("Authorization", APIKey) // APIKey를 이용해서 권한을 얻는다.
												.asJson();						// response는 JSon으로
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

	}
}
