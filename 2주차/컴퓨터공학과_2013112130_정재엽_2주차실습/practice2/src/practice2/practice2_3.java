package practice2;

public class practice2_3 {
	public static void main(String[] args) {
		
		int i, j;
		int boundary = 7 / 2; // 중간지점 표시
		for(i = 0 ; i < 7 ; i++){
			//위의 삼각형 찍기, i = 0, 1, 2, 3
			if( i  <= boundary ) {
				for( j = 0 ; j < 4-i ; j++) { 
					System.out.print(" ");
				}
				for(j=0 ; j < 2*i+1 ; j++ ) {
					System.out.print("*");
				}
				System.out.println("");
			}
			
			//아래 삼각형 찍기 4, 5, 6
			if( i > boundary) {
				for(j = 0 ; j < i-2 ; j++) {
					System.out.print(" ");
				}
				for(j=0 ; j < 11-2*(i-1) ; j++ ) {
					System.out.print("*");
				}
				System.out.println("");
			}
		}
	}
}

// 문제 7번잘못된 것2개 