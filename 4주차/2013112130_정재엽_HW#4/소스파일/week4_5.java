

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class week4_5 {
	public static void main(String args[]) {
		
		
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\skyla\\Desktop\\test.txt"));
			BufferedWriter wr = new BufferedWriter(new FileWriter("C:\\Users\\skyla\\Desktop\\ouput.txt"));
			
			String str;
			while((str = br.readLine()) != null) {
				
				String[] output = str.split(" ");
				// split나눌때 splie(" |,") 두개 토큰 사용 가능 
				for(String temp : output)
				{
					String[] output2 = temp.split(",");
					for(String temp2 : output2) {
						wr.write(temp2);
						wr.newLine();
					}
				}
				
				System.out.print(str + "\r\n");
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
