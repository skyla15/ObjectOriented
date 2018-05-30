
public class week3_7_1 {
	public static void main(String[] args) {
		long a = 0, b = 1, c;
		int i;
		System.out.println("1 :" + a);
		System.out.println("2 :" + b);
		for ( i = 2 ; i <= 10; i++) {			
			c = a+b;
			a = b;
			b = c;
			System.out.println((i+1) +" :" + c + " ");
		}
		
	}
}

