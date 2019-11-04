package apiTest.kakaoMap;

import java.util.HashMap;
import java.util.List;

import lombok.Data;

////@Getter, @Setter, @RequiredArgsConstructor, @ToString, @EqualsAndHashCode 을 모두 적용해줌
//@Data 오잉 왜 안됨
public class KakaoGeoRes {
	private HashMap<String, Object> meta;
	private List<Documents> documents;
	public HashMap<String, Object> getMeta() {
		return meta;
	}
	public void setMeta(HashMap<String, Object> meta) {
		this.meta = meta;
	}
	public List<Documents> getDocuments() {
		return documents;
	}
	public void setDocuments(List<Documents> documents) {
		this.documents = documents;
	}
	
	
}

//@Data
class Documents {
    private HashMap<String, Object> address;
    private String address_type;
    private Double x;
    private Double y;
    private String address_name;
    
    public HashMap<String, Object> getAddress() {
		return address;
	}
	public void setAddress(HashMap<String, Object> address) {
		this.address = address;
	}
	public String getAddress_type() {
		return address_type;
	}
	public void setAddress_type(String address_type) {
		this.address_type = address_type;
	}
	public Double getX() {
		return x;
	}
	public void setX(Double x) {
		this.x = x;
	}
	public Double getY() {
		return y;
	}
	public void setY(Double y) {
		this.y = y;
	}
	public String getAddress_name() {
		return address_name;
	}
	public void setAddress_name(String address_name) {
		this.address_name = address_name;
	}
	public HashMap<String, Object> getRoad_address() {
		return road_address;
	}
	public void setRoad_address(HashMap<String, Object> road_address) {
		this.road_address = road_address;
	}
	private HashMap<String, Object> road_address;
    
    
}