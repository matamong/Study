package apiTest.VO;

public class JsonVO {
	String x; 
	String y ;
	String title;
	
	
	public JsonVO(String x, String y, String title) {
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "JsonVO [x=" + x + ", y=" + y + ", "
				+ "title=" + title + "]";
	}
	
	
}
