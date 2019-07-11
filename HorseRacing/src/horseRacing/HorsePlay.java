package horseRacing;

import java.util.Arrays;
import java.util.Scanner;


public class HorsePlay {
	
	private int inputMoney;
	private int crrMoney;
	
	String[] showHorse  = {"날쌩마", "번개", "5G", "조랑말"};


	HorsePlay(int inputMoney, int crrMoney){
		this.inputMoney = inputMoney;
		this.crrMoney = crrMoney;
	}
	
	public void run() {
		String chHorseName;
		int chHorseNum = show();
		System.out.println(chHorseNum);
		
		chHorseName = showHorse[chHorseNum-1];
		System.out.println(chHorseName);
		
		HorseThread ht = new HorseThread(chHorseName);
		Thread htTh = new Thread(ht, "ht");
		
		htTh.start();
	//	gameGo();
	//	boolean result = compare(chHorse);
		
	//	Give give = new Give(result);
		
	}
	
	public int show() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("-------------------------------");
		for(int i=0; i<showHorse.length;i++) {
			System.out.printf("[%d]번 말 : %2s%n", i+1, showHorse[i]);
		}
		System.out.println("-------------------------------");

		System.out.println("어느 말에 배팅하시겠씀까? >> ");
		int chHorse = sc.nextInt();
		
		return chHorse;
	}
	
	public void go() {
		//쓰레드
	}

}
