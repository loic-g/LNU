package lg222sv_assign1;

import java.lang.Math;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Distance {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in); //Setup the scanner 
		
		System.out.println("Write the two coordinates (in the form (x,y)): ");//Get the coordinates as text 
		String Fcoor = sc.nextLine();
		String Scoor=sc.nextLine();		
		sc.close();
		
		String sx1 = Fcoor.substring(1,2);//Take the 2nd character of the line and put it in a string
		String sy1 = Fcoor.substring(3,4);//Take the 4th character of the line and put it in a string
		String sx2 = Scoor.substring(1,2);//Take the 2nd character of the line and put it in a string
		String sy2 = Scoor.substring(3,4);//Take the 4th character of the line and put it in a string
		
		Double x1 = Double.valueOf(sx1);//Change the type of the variables to double to be able to use the number and do calculations
		Double y1 = Double.valueOf(sy1);
		Double x2 = Double.valueOf(sx2);
		Double y2 = Double.valueOf(sy2);
		
		
		Double distance = Math.sqrt(Math.pow((x1-x2),2)+Math.pow((y1-y2),2));//execute the formula that was shown at the beginning 
		
		DecimalFormat DF = new DecimalFormat("0.###");
		String Result = DF.format(distance);
				
		System.out.println("The distance between the two points is: "+Result);//Show the result nicely
	}

}
