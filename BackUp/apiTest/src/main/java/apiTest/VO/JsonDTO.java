package apiTest.VO;

public class JsonDTO {
	private String x;
	private String y;
	private String latlng;
	private String title;
	
	
	public JsonDTO(String x, String y, String latlng, String title) {
		this.x = x;
		this.y = y;
		this.title = title;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}
	
	
	
	public String getLatlng() {
		return latlng;
	}

	public void setLatlng(String latlng) {
		this.latlng = latlng;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "JsonDTO [x=" + x + ", y=" + y + ", "
				+ "latlng=" + latlng + ", title=" + title + "]";
	}
	
	
}
