package lg222sv_assign1;

import java.util.Scanner;
public class Quote {

	public static void main(String[] args) {
				
		Scanner Myscanner = new Scanner(System.in); // Scan to see what is my answer
		
		System.out.print("Write a line of text: "); 
		String QuoteInText = Myscanner.nextLine(); // Put the answer into the variable
		
		System.out.println("Quote: " + "\"" + QuoteInText + ".\""); //Repeat the sentence with quotation marks
		
		Myscanner.close(); //Close the scanner
	}
}