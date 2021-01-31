package lg222sv_assign1;

import java.text.DecimalFormat;
import java.util.Scanner;

public class WindChill {

	public static void main(String[] args) {
		
		//Setup the Scanner 
		Scanner sc = new Scanner(System.in);
		
		//Get information from the user
		System.out.println("Temperature (C): ");
		double temp = sc.nextDouble();
		System.out.println("Wind speed (m/s): ");
		double wspeed = sc.nextDouble();
		sc.close();
		
		//Calculation 
		wspeed = wspeed*3.6;//Change the m/s to km/h
		
		double twc = 13.12+(0.6215*temp)-(11.37*Math.pow(wspeed,0.16))+(0.3965*temp*Math.pow(wspeed,0.16));
		
		//Round off the result to one decimal
		DecimalFormat DF = new DecimalFormat("0.#");
		String Result = DF.format(twc);
		System.out.println("Wind Chill Temperature (C): "+Result);
	
	}

}
