package lg222sv_assign1;

import java.util.Scanner;

public class Change {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); //scan for the Price
		System.out.println("Price: ");
		double P =sc.nextDouble(); //Need to use double variable to get the number after the point
			
		System.out.println("Payment: ");// Scan for the payment
		int payment=sc.nextInt();
		
		sc.close(); //close the scanner
		
		int price = (int)P; //change to int variable to only get the integer as 0.1SEK does not exist 
		
		// Use division and modulus
		int dif = payment-price;
		
		System.out.print("\nChange: " +dif+ "  kronor");
		
		//Calculate for every single bills and coins
		int Help = dif/1000;//Use the Help variable as help to be able to calculate without changing the values of the other variables
		int Thousand = Help;
		Help = dif%1000;
		int FiveHundred = Help/500;
		Help = Help%500;
		int TwoHundred = Help/200;
		Help = Help%200;
		int OneHundred = Help/100;
		Help = Help%100;
		int Fifty = Help/50;
		Help = Help%50;
		int Twenty = Help/20;
		Help = Help%20;
		int Ten = Help/10;
		Help = Help%10;
		int Five = Help/5;
		Help = Help%5;
		int Two = Help/2;
		Help = Help%2;
		int One = Help/1;
		
		
		
		System.out.println("\n1000kr bills: "+Thousand+"\n500kr bills: "+FiveHundred+"\n200kr bills: "+TwoHundred+"\n100kr bills: "+OneHundred);
		System.out.println("50kr bills: "+Fifty+"\n20kr bills: "+Twenty+"\n10kr coins: "+Ten+"\n5kr coins: "+Five+"\n2kr coins: "+Two+"\n1kr coins"
+ ": "+One);
		
	}

}
