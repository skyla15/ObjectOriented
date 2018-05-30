import java.util.Scanner;

public class week3_7_2 {
	public static void main(String[] args) {
		boolean chkScan = false;
		long a = 0, b = 1, c;
		int cnt;
		int i;
		Scanner scan = new Scanner(System.in);
		
		while(chkScan != true) {
		System.out.print("몇 항까지 출력하시겠습니까?");
		cnt = scan.nextInt();
		if(cnt > 2) {
			chkScan = true;
			System.out.println("1: " + a);
			System.out.println("2: " + b);
			for ( i = 2 ; i < cnt; i++) {			
				c = a+b;
				a = b;
				b = c;
				System.out.println((i+1) + ": "+ c );	
			}
		}
		else System.out.println("잘못된 수를 입력하셨습니다.");
		}
		System.out.println("프로그램을 종료합니다.");
	}
}
