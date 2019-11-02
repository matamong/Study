package apiTest.json.util.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
 
import org.json.JSONObject;
import org.json.XML;

public class XmlToJSONTest {
	//public static int INDENT_FACTOR = 4;
	
	public static void main(String[] args) throws MalformedURLException, IOException {
		String apiUrl = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic?bgnde=20140301&endde=20140430&pageNo=1&numOfRows=10&ServiceKey=";
		String serviceKey = "eB2iL1sXS22iTD%2Bxbg0WGqE4ZqV1xwJqcFNiI5TGcXSl%2F9Ix1%2B07zpPXO2uaPCXPjzHvOZt4B8gCTZK3yWIX4w%3D%3D";
		
		//접속정보
		HttpURLConnection conn = (HttpURLConnection)new URL(apiUrl+ serviceKey).openConnection();
		//접속
		conn.connect();
		
		//buffer stream 으로 받기
		BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
		//buffer로 읽기
		BufferedReader reader = new BufferedReader(new InputStreamReader(bis));
		//String buffer 생성
		StringBuffer st = new StringBuffer();
		
		//StringBuffer에 String 붙이기
		String line;
		while((line = reader.readLine()) != null) {
			st.append(line);
		}
		
		//json 오브젝트에 만들어놓은 string buffer을 xml에서 json으로 변환
		JSONObject xmlJSONObj  = XML.toJSONObject(st.toString());
		
		String jsonPrettyPrintString = xmlJSONObj.toString(INDENT_FACTOR);
		System.out.println(jsonPrettyPrintString);
		
	}
}
