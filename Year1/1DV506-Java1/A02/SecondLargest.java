package lg222sv_assign2;

import java.util.Scanner;

public class SecondLargest {

	public static void main(String[] Args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Provide 10 integers: ");		
		int large=Integer.MIN_VALUE, Secondlargest=Integer.MIN_VALUE, Integers = 10, num; //setup all the variables
		for (int i=0;i<Integers;i++) {
			num = sc.nextInt();//get each integer one by one
			
			
			if (num>large) {
				Secondlargest = large; //to make sure that when a new number larger than large comes to number already in large goes into Secondlarge 
				large = num;
				continue;
			}
			else if ((num>Secondlargest) && (num<large)) {
				Secondlargest = num;
				continue;
			}
			
		}
		sc.close();
		System.out.println("The second largest is: "+Secondlargest);
	}
}
