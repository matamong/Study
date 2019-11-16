package apiTest.VO;

public class MapDTO {
	private String email;
	private int x; 
	private int y ;
	private String special;
	private String title;
	private String map_address;
	
	
	public MapDTO(String email, int x, int y,String special, String title, String map_address) {
		this.email = email;
		this.x = x;
		this.y = y;
		this.special = special;
		this.title = title;
		this.map_address = map_address;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public String getSpecial() {
		return special;
	}


	public void setSpecial(String special) {
		this.special = special;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getMap_address() {
		return map_address;
	}


	public void setMap_address(String map_address) {
		this.map_address = map_address;
	}
	
	
	
}
