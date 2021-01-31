package lg222sv_assign2;

import java.util.Random;
import java.util.Scanner;

public class HighLow {

	public static void main(String[] args) {
		int count=0; //Setup count variable
		int guess;
		Random rd = new Random();//setup the random 
		int nb = rd.nextInt(100)+1;//get number from 0 to 100
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Guess an integer between 1 and 100!");
		
		do {
			count++;
			System.out.println("Guess"+count+": ");
			guess = sc.nextInt();
			
			if (guess>100 || guess<1) {//To make sure that the user will put the correct integer
				System.err.println("  Please enter an interger between 1 and 100");
				count--;
				continue;
			}
			
			else if(nb==guess) { //if number is the same as the random one create, then win and show how many times he/she needed to guess it
				System.out.println("  Correct answer after only "+count+" guesses - Excellent!");
				break;
			}
			
			else if(nb>guess) {
				System.out.println("  Clue: Higher"); //give the clue higher if the number is lower than the random one
				continue;
			}
			
			else if(nb<guess) {
				System.out.println("  Clue: Lower");//give the clue lower if the number is higher than the random one
				continue;
			}
					
		} while (count<10);
		sc.close();
		
		if (count==10) { //if the user do not find it after 10 tries 
			System.out.println("\nYour 10 free tries are over! Now you have to pay 900SEK/month to try again");
			
			
		}
	}

}
