
public class week3_8 {
	public static void main(String args[]) {
		//8-1, 문자열 길이 출력
		String str1 = "abcdef";
		System.out.println("8-1번 : " + str1.length());
		System.out.println("");
		
		//8-2 두 문자열 같음 비교, 8-3두 문자열들에 대해 대소문자 구분않고 같은지 비교
		String str2 = "abcdef"; 
		String str3 = "ABCDEF";
		System.out.println("8-2번 : " +str1.equals(str3)); //대소구분
		System.out.println("8-3번 : " +str1.equalsIgnoreCase(str3)); //대소구분없이
		System.out.println("");
		
		//8-4, 문자열의 3번째 문자가 무엇인지 출력 
		String str4 = "abcdef";
		System.out.println("8-4번 : " +str4.charAt(2));
		System.out.println("");
		
		//8-5, 각 문자열의 3~5번째 부분문자열출력 
		System.out.println("8-5번, 각 문자열의 3~5번째 부분 출력");
		System.out.println(str1.substring(2,5));
		System.out.println(str3.substring(2,5));
		System.out.println("");
		
		//8-6, abc와 def로 분리하여 arr배열에 넣고 배열 출력 
		String str5 = "abc,def";
		String arr[] = new String[2];
		arr = str5.split(",");
		System.out.println("8-6번 : ");
		for(int i = 0 ; i< arr.length ; i++) {
			System.out.println(arr[i]);
		}
		System.out.println("");
				
		
		//8-7, abcdef가 adf로 시작하는 지 검색 
		String str6 = "abcdef";
		System.out.println("8-7번 : ");
		System.out.println(str6.startsWith("adf"));
		System.out.println("");		
		
	}
}

/*
if ( str.equals("a")) => str과 a의 문자열 확인 
if(str == "a") a와 str 메모리 위치를 비교. 
*/