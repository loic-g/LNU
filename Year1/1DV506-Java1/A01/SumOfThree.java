 package lg222sv_assign1;

import java.util.Scanner; //get the Scanner 

public class SumOfThree {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Provide a three digit number: ");
		int digit = sc.nextInt(); // Put the info into the variable digit
		sc.close(); // close the scanner
		
		int first = digit/100; //to get the 1st digit
		int second = (digit/10)%10; //to get the 2nd digit
		int third = digit%10; //to get the Last digit
		
		int sum = first+second+third; //Add all the digits together
		System.out.println("Sum of digits: "+sum); //Print the result
	}

}
