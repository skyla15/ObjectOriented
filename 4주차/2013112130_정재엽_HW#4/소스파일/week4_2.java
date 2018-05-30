import java.util.Scanner;

public class week4_2 {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		int x = 0;
		
		for(int i = 1 ; i < 500 ; i ++) {
			int p_sum = 0; //완전수인지 확인하기 위해 숫자 저장
			for(int j = 1 ; j < i ; j ++) {
				if(i%j == 0 ) p_sum +=j; 
				//나누었을때 0 이라면 j는 i의 약수, 약수의 합을 p_sum에 저장
			}
			if(p_sum == i)System.out.print(i + " "); //i와 p_sum이 같다면 완전수 
		}
		
	}
}
