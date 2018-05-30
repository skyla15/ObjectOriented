import java.util.Scanner;

public class week3_2 {
	public static void main(String args[]) {
		int x,y;
		int max=0;
		int min=0;
		int count = 0;
		Scanner scan = new Scanner(System.in);
		System.out.print("숫자 입력 x : ");
		x = scan.nextInt();
		System.out.print("숫자 입력 y : ");
		y = scan.nextInt();
		
		System.out.println("count 초기값 : " + count);
		
		//2_1번 
		if(x>20 && x< 60) count++; 
		
		System.out.println("count 이후값 : " + count);
		
		//2_2번 
		if(x>y) {
			max = x;
			min = y;
		}
		else if(x < y ) {
			max = y;
			min = x;
		}
		System.out.println("max : " + max + ", min : " + min);
		
		//2_3번 
		System.out.println("y = "+betweenXY(x,y));
		
	}
	

	public static int betweenXY(int x, int y) {
		if( x>= 1 && x <= 20) y = x;
		else System.out.println("y의 값이 변하지 않았습니다.");
		return y;
	}

}


//소스코드가 아니라 
//50이하이면 count를 증가
/*
* ex
*/
