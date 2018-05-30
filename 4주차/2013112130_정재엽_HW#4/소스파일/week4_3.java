import java.util.Scanner;

public class week4_3 {
	public static void main(String args[]) {
		Scanner num = new Scanner(System.in);
		System.out.print("숫자 입력: ");
		int number = num.nextInt();
		int temp = number;
		int result = 0; //num을 거꾸로 변환해서 담음 
		
		
		while(temp != 0) {
			result = result*10 + temp%10;
			temp = temp/10;
//			System.out.print("변환값 : "  + result);
//			System.out.print(", 다음값 : "  + temp + "\n");
		}
		
		if(number == result)
			System.out.println(number + "는 회문수입니다.");
		else
			System.out.println(number + "는 회문수가 아닙니다.");
		
	}
}
