package Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Unbox {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		String str = reader.readLine();
		System.out.print(unpackFunc(str.toCharArray()));
		
	}
	
	public static String unpackFunc(char[] str) {
		
		String finalStr = "";
		int containerCount = 0, biggestContainer = 0, ratio = 0;
		
		for(int i = 0; i < str.length; i++) {
			
			if(Character.isLetter(str[i]) && containerCount == 0)
				finalStr += str[i];
			
			if (Character.isDigit(str[i]) && containerCount == 0)
				//почему выдает код элемента???
				//ratio = Integer.valueOf(str[i]);
                ratio = Integer.parseInt(String.valueOf(str[i]));				
			
			//поиск первого контейнера
			if (str[i] == '[') {				
				containerCount ++;
				if(containerCount == 1)
					biggestContainer = i;				
			}
							
			//закрытие контейнера
			if (str[i] == ']') {				
				containerCount --;
				if (containerCount == 0) {
					finalStr += unpackFunc(Arrays.copyOfRange(str,biggestContainer+1,i)).repeat(ratio);
					ratio = 0;
					biggestContainer = 0;					
				}
			}
		}
		
		return finalStr;
		
	}
}