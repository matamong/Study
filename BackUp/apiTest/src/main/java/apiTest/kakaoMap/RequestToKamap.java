package apiTest.kakaoMap;

import java.net.URLEncoder;
import java.util.HashMap;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

public class RequestToKamap {
	public static void main(String[] args) {
		getConnect();
	}
	public static void getConnect() {
		String APIKey = "6740ac764429e04197110a045a9b8482";
		
		HashMap<String, Object> map = new HashMap(); //결과 담을 map
		
		//Unirest 라이브러리를 이용해서 Http 요청을 보내서 접속
		try {
			String apiURL = "https://dapi.kakao.com/v2/local/search/address.json?query=" 
							+ URLEncoder.encode("address", "UTF-8");
			HttpResponse<JsonNode> response = Unirest.get(apiURL)				//해당 API URL에서
												.header("Authorization", APIKey) // APIKey를 이용해서 권한을 얻는다.
												.asJson();						// response는 JSon으로
			
			//받아온 데이터를 jackson의 objectMapper을 이용해서 json으로 변환
			ObjectMapper objectMapper = new ObjectMapper();
			//단일 리스트 객체가 있으면 싱글 값 같게 인식시켜줌 (ex: "family" : ["me"] -> "family" : "me" )
			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			
			//지정된 VO에 응답받은 데이터를 셋팅
			KakaoGeoRes bodyJson = objectMapper.readValue(response.getBody().toString(), KakaoGeoRes.class);
			
			
			double x = bodyJson.getDocuments().get(0).getX();
			System.out.println(x);
			bodyJson.getDocuments().get(0).getY();
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
