package lg222sv_assign1;

import java.util.Scanner; // importing the Scanner
public class Time {

	public static void main(String[] Args) {
			
		Scanner Second = new Scanner(System.in);// Setup the scanner
		System.out.print("Give a number of second: ");//ask for the amount of seconds
		int sec = Second.nextInt();//Get the number of seconds 
		
		int Hour = sec/3600; //Get how many hours there is 
		int min = (sec%3600)/60; //Take the remainder of the first equation (seconds) and then get minutes (divide by 60)
		int sec2 = (sec%3600)%60;// Take the Remainder of the Remainder to get the remaining seconds
		
		Second.close();
		System.out.println("This corresponds to: " + Hour+" hours, "+min + " minutes and  " + sec2 + " seconds"); //shows nicely all the variables
	}
}
