package lg222sv_assign1;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Area {

	public static void main(String[] Args) {
		Scanner sc = new Scanner(System.in); //Setup the scanner
		
		System.out.print("Provide Radius: ");//Get the value for the radius
		double radius = sc.nextDouble();
		
		sc.close(); //Close the scanner as we don't need it anymore
		
		double area = Math.PI*(radius*radius); //use the formula from the area of a circle Pi*Radius^2
		
		//Get the answer with 3 decimals
		DecimalFormat DF = new DecimalFormat("0.#");
		String OneD = DF.format(area);
		
		System.out.println("Corresponding area is "+OneD);//show the answer
		
	}
}
