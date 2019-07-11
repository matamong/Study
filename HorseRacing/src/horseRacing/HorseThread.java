package horseRacing;

import java.util.Random;

public class HorseThread implements Runnable{
	

	HorseThread(String chHorseName){
		this.chHorseName = chHorseName;
	}
	
	private String chHorseName;
	int targetNum=100;
	int sum=0;
	
	Random random = new Random();

	@Override
	public void run() {
		StringBuffer sb = new StringBuffer();
		int count=0;
		while(true) {
			sum = sum+random.nextInt(10);
			sb.delete(0, sb.toString().length());
			
			if(sum >= targetNum) {
				sum = 100;
				for(int i=0; i<sum; i++) {
					sb.append('*');
				}
				System.out.println(chHorseName+" :	"+sb);
				break;
			}else {
				for (int i=0; i<sum;i++) {
					sb.append('*');
				}
				System.out.println(chHorseName+" :	"+sb);
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return count;
		}//while의 끝
  }
}

