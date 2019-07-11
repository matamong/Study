package horseRacing;

import java.util.Scanner;

public class HorseInput {
	
	public void start() {
		

		int inputMoney;
		int crrMoney =0;
		
		inputMoney = this.input();
		crrMoney += inputMoney; 
		
		HorsePlay play = new HorsePlay(inputMoney, crrMoney);
		play.run();
	}
	
	
	public int input() {
		Scanner sc = new Scanner(System.in);
		int inputMoney, crrMoney=0;
		
		while(true) {
		System.out.println("배팅 할 돈을 입력해주세요.(종료:0) >> ");
		String money = sc.nextLine();
		inputMoney = Integer.parseInt(money);
		
		if(inputMoney > 0) {
			System.out.println("***환영합니다.***");
			break;
		}
		
		if(inputMoney < 0 ) {
			System.out.println("양수를 입력하세요.");
			continue;
		}
		if(inputMoney == 0) {
			System.out.println("프로그램을 종료합니다.");
			System.exit(0);
		}
		
		}
		
		return inputMoney;
		
	}
	
	

}
