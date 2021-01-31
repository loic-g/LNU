package lg222sv_assign2;

import java.util.Scanner;

public class Backwards {

	public static void main(String[] args) {
		//Setup the scanner and get the line of text 
		Scanner sc = new Scanner(System.in);
		System.out.print("Provide a line of text: ");
		String text = sc.nextLine(); // variable that will get the line of text 
		sc.close();
		int count=0;//setup the count variable to create the condition for the while loop 
		
		int tlength = text.length();
		char reverse;
		
		StringBuilder bf = new StringBuilder(); //Setup the StringBuilder to be able to write the backwards text
		
		while (count<tlength) { //As long as the count will be less than the length of the text of the while will work 
			reverse = text.charAt((tlength-1)-count); // take the last character, then second last, then third last, etc until position 0
			bf.append(reverse);// Put it into the StringBuilder
			count++;
		}
		
		System.out.println("Backwards: "+ bf); //Print the result
		}

	}


