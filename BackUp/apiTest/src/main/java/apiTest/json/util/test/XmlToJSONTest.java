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
	public static int INDENT_FACTOR = 4;
	
	public static void main(String[] args) {
		String apiurl = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic?bgnde=20140301&endde=20140430&pageNo=1&numOfRows=10&ServiceKey=";
		String serviceKey = "eB2iL1sXS22iTD%2Bxbg0WGqE4ZqV1xwJqcFNiI5TGcXSl%2F9Ix1%2B07zpPXO2uaPCXPjzHvOZt4B8gCTZK3yWIX4w%3D%3D";
	}
}
