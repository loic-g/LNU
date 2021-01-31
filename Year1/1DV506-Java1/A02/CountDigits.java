package lg222sv_assign2;

import java.util.Scanner;

public class CountDigits {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Provide a positive integer: ");
		String Pint = sc.next();
		sc.close();
		int length = Pint.length();
		int Zero=0,Even=0,Odd=0;
		
		for (int i=0;i<length;i++) {
			char number = Pint.charAt(i); //check every single character of the integer
			int Number=Character.getNumericValue(number); //transform the char into a int variable
			
			if (Number==0) {
				Zero++;
				continue;
			}
			if ((Number%2==0) && !(Number==0)) {
				Even++;
			}
			else {
				Odd++;
			}
		}
		System.out.println("Zeros: "+Zero+"\nOdd: "+Odd+"\nEven: "+Even);

	}

}
