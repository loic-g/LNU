package lg222sv_assign1;

import java.util.Random; //Importing the function Random from the library

public class RandomSum {

	public static void main(String[] args) {
		Random rd = new Random(); //setup the random function 
		
		int nb1 = rd.nextInt(100)+1; //Get a random value between 1 and 100
		int nb2 = rd.nextInt(100)+1;
		int nb3 = rd.nextInt(100)+1;
		int nb4 = rd.nextInt(100)+1;
		int nb5 = rd.nextInt(100)+1;
		
		int sum = nb1+nb2+nb3+nb4+nb5; //Get the sum of all the random number
		System.out.println("Five random numbers: "+nb1+" "+nb2+" "+nb3+" "+nb4+" "+nb5); //Show all the random integer 
		System.out.println("\nTheir sum is "+sum); //Show the result of the sum

	}

}
