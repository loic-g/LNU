package lg222sv_assign2;

import java.util.Scanner;

public class CountA {

	public static void main(String[] Args) {
		
		//get the info with scanner 
		Scanner sc = new Scanner(System.in);
		System.out.print("Provide a line of text: ");
		String text =sc.nextLine();
		sc.close();
		
		int tl = text.length(); //to get the length of the text
		int i = 0, na=0, nA=0; //all the count variables
		char c, a = 'a', A='A'; 
		
		
		//Use the loop to check every single character
		while (i<tl) { 
			c = text.charAt(i); //check for every single character of the text
			
			if (c == a) { //if the character is a 'a' then add 1 to the count for 'a'
				na++;
			}
			else if (c == A){//if the character is a 'A' then add 1 to the count for 'A'
				nA++;
			}				
			i++;
		}
			
		System.out.println("Number of 'a': "+na+"\nNumber of 'A': "+nA); //Print the result at the end
			
	}

}
