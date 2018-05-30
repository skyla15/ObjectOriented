
public class week3_5 {
	public static void main(String args[]) {
		int[] dice1 = new int[] {1,2,3,4,5,6};
		int[] dice2 = new int[] {1,2,3,4,5,6}; 

		int cnt = 0;
		
		for(int i = 0; dice1.length > i ; i++) {
			for(int j = 0 ; dice2.length > j ; j++) {
				if(dice1[i]+dice2[j] == 6) cnt++;
				
			}
		}		
		System.out.println(cnt);
		
	}
}
/*

1 5
2 4
3 3
4 2
5 1

*/
