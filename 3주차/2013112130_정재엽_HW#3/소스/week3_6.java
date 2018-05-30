import java.util.Scanner;

public class week3_6 {
	public static void main(String args[]) {
		int height;
		int weight;
		float avg_weight;
		
		
		Scanner scan = new Scanner(System.in);
		System.out.print("키 입력 : ");
		height = scan.nextInt();
		System.out.print("몸무게 입력 : ");
		weight = scan.nextInt();
		
		avg_weight = (float)((height-100)*(0.9));
		System.out.println("귀하의 정상체중은 " + avg_weight + "입니다");
		if(avg_weight > weight ) System.out.println("저체중입니다.");
		else if(avg_weight == weight ) System.out.println("정상입니다.");
		else if(avg_weight < weight ) System.out.println("과체중입니다.");
	}
}
