package springbook.user.domain;

public enum Levelu {
	BASIC(1), SILVER(2), GOLD(3);
	
	private final int value;
	
	Levelu(int value){
		this.value = value;
	}
	
	public int intValue() {
		return value;
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
