package chap02;


/*
 * 보초법으로 키 값이 저장되어있는 배열의 인덱스를 찾아보자. 
 */

public class SentinelSearch {
	public static void main(String[] args) {
		int[] arr = new int[6];
		int key = 10;
		int result;
		
		for(int i=0; i < arr.length-2; i++) {   //배열의 마지막만 빼고 랜덤 값 집어넣기
			arr[i] = (int)(Math.random()*10)+1;
		}
		
		result = sentinel(arr, arr.length, key);
		
		if(result == -1)
			System.out.println("키 값이 없습니다.");
		else
			System.out.println("키 값이 저장되어있는 인덱스는 " + result + "입니다.");
		
		for(int i=0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
		
	}
	
	static int sentinel(int[] arr, int length, int key) {
		int i  = 0;
		arr[arr.length-1] = key;   //배열의 마지막에 키 값을 넣음
		
		while(true) {
			if(arr[i] == key)
				break;
			i++;
		}
		return i == arr.length-1 ? -1 : i;
	}

}
