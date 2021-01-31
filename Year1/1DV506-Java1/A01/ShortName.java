package lg222sv_assign1;

import java.util.Scanner;

public class ShortName {

	public static void main(String[] Args) {
		Scanner sc = new Scanner(System.in);//Create a scanner 
		
		System.out.println("First name: ");//Get the First Name
		String Fname = sc.nextLine();
		System.out.println("Last name: ");//Get the Last Name
		String Lname = sc.nextLine();
		sc.close(); //Close 
		
		Fname = Fname.substring(0,1); //Get the 1st character of the String variable
		Lname = Lname.substring(0,4); //Get the character from the 1st one to the 4th one
		
		System.out.println("Short name: "+Fname+". "+Lname); //Print the result
	}
}
