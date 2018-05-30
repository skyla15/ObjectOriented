package practice2;
import java.util.Scanner;

public class practice2_8 {
	public static void main(String args[]) {
		double km = 0;
		double mile = 0;
		System.out.print("마일을 입력하시오: ");
		Scanner sc = new Scanner(System.in);
		mile = sc.nextDouble();
		km = mile*1.609;
		
		System.out.println(mile+ "마일은"+ km + " 킬로미터입니다.");
		
		//sc.close(); 가 있으면 다른 함수에서 사용할 수가 없음
		
	}
}
