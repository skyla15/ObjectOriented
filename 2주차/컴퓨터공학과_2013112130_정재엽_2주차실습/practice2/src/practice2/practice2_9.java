package practice2;
import java.util.Scanner;

public class practice2_9 {
	public static void main(String[] args) {
		double c;
		double f;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("화씨 온도 :");
		f = sc.nextDouble();
		c = 5.0*(f-32)/9;
		
		System.out.println("화씨 " + f + "도 = 섭씨 " + c + "도");
		
		
		
		
	}
}
