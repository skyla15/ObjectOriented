
public class week3_4 {
	public static void main(String args[]) {
		int sum = 0;
		for(int i = 1 ; i <= 100 ; i++ ) {
			if(i%4 == 0)
				sum += i;
			if(i%3 == 0)
				sum +=i;
			if(i%12 ==0)
				sum -= i; //위에서 두번 더해진 수는 빼준다.
		}
		System.out.println(sum);
	}
}

