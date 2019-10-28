package springbook.user.domain;

public class User {
	String id;
	String name;
	String password;
	Levelu levelu;
	int login;
	int recommend;
	String email;
	
	
	public User() {}
	
	public User(String id, String name, String password, Levelu levelu, int login, int recommend, String email) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.levelu = levelu;
		this.login = login;
		this.recommend = recommend;
	}
	
	public void upgradeLevel() {
		Levelu nextLevel = this.levelu.nextLevel();
		if(nextLevel == null) {
			throw new IllegalStateException(this.levelu + "은 업그레이드가 불가능합니다.");
		}else {
			this.levelu = nextLevel;
		}
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Levelu getLevelu() {
		return levelu;
	}

	public void setLevelu(Levelu levelu) {
		this.levelu = levelu;
	}

	public int getLogin() {
		return login;
	}

	public void setLogin(int login) {
		this.login = login;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
