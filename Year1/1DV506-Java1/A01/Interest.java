package lg222sv_assign1;

import java.util.Scanner;
public class Interest {

	public static void main(String[] args) {
				
		Scanner initial = new Scanner(System.in); // scan the initial value we put in 
		
		System.out.print("Initial savings: ");// Ask for the initial value
		double init = initial.nextDouble();//Get the information for the initial savings
		
		System.out.print("Interest rate (in percentage): ");// Ask for interest rate 
		double P = initial.nextDouble();//get the percentage rate 
		
		initial.close();//Close both scanner
		
		double H = init*Math.pow((P/100+1),5);//Use the equation for recurrence interest 
		int S = (int) Math.round(H); //Round off the variable and then Convert "double" variables into "int" to get integers 
		
		System.out.println("The value of your saving after 5 years is: " + S); //Show it nicely 
	}
}
