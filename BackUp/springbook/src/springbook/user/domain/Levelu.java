package springbook.user.domain;

public enum Levelu {
	GOLD(3, null), SILVER(2, GOLD), BASIC(1, SILVER); 
	
	private final int value;
	private final Levelu next;
	
	Levelu(int value, Levelu next){
		this.value = value;
		this.next = next;
	}
	
	public int intValue() {
		return value;
	}
	
	public Levelu nextLevel() {
		return this.next;
	}
	
	public static Levelu valueOf(int value) {
		switch(value) {
			case 1: return BASIC;
			case 2: return SILVER;
			case 3: return GOLD;
			default: throw new AssertionError("Unknown value" + value);
		}
	}
	

}
