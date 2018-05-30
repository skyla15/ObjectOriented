

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Week4_4 {
	public static void main(String args[]) {
		
		
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\skyla\\Desktop\\test.txt"));
			BufferedWriter wr = new BufferedWriter(new FileWriter("C:\\Users\\skyla\\Desktop\\ouput1.txt"));
			
			String str;
			while((str = br.readLine()) != null) { //버퍼로 들어온 파일을 한줄씩 읽는다.				
				System.out.print(str + "\r\n");
				wr.write(str); //버퍼로 들어온 파일을 한줄씨 writer를 통해 작성한다. 
				wr.newLine(); //한줄쓰고 한줄씩 띄어준다.
			}
			br.close();
			wr.close();
		}
		catch(IOException e){
			System.out.println(e.getMessage());			
		}
		finally{
			System.out.println("program end");
		}
	}
}
