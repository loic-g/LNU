package lg222sv_assign1;

import java.util.Scanner; //Introduce the Scanner 
public class Fahrenheit {

	public static void main(String[] args) {
	
		Scanner scan = new Scanner(System.in); //Setup the Scanner 
		
		System.out.print("Provide a temperature in Fahrenheit: ");//Get the value for the temperature
		double fahren = scan.nextDouble();
		
		scan.close();//Close the scanner 
		
		double celsius = (fahren-32)*5/9; //calculation to go from Fahrenheit to Celsius
		System.out.println("Corresponding temperature in Celsius is " + celsius);//Show the corresponding temperature	
	}
}
