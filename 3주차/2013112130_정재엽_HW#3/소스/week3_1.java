
public class week3_1 {
	public static void main(String args[]) {
		
		int arr[][] = new int[2][3];
		int arr2[][] = new int[3][2];
		int value = 1; 
		
		System.out.println("Before Transpose");
		for(int i = 0 ; i < arr.length ; i++ ) {
			for(int j = 0 ; j < arr[0].length ; j++) {
				arr[i][j] =  value;
				value++;
				System.out.print(arr[i][j] + " ");				
			}
			System.out.println("");			
		}		
		
		
		System.out.println("After Transpose");
		arr2 = transpose(arr);
		for(int i = 0 ; i < arr2.length ; i++) {
			for(int j = 0 ; j < arr2[0].length ; j++) {
				System.out.print(arr2[i][j]+ " ");
			}
			System.out.println("");
		}
		
	}
	
	public static int[][] transpose(int[][] input){
		int[][] arr = new int[input[0].length][input.length];
		
		for(int i = 0 ; i < input[0].length ; i ++) {
			for(int j = 0 ; j < input.length ; j++) {
				arr[i][j] = input[j][i];
			}
		}
		return arr;		
	}		
}

/*
 *  int arr[][] = new int[3][];
 *  for(int i = ; i < arr.length ; i++)
 *  	int arr[i] = new int[2];
 */
