package lg222sv_assign2;

import java.util.Scanner;

public class Triangle {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Provide an odd positive integer: ");
		int OPI = sc.nextInt();
		sc.close();
		int count = 0;
		char star = '*',space=' ';
		
		StringBuilder RAT = new StringBuilder();// Right Angled Triangle
		
		//to make sure that our number is an odd positive integer
		if (OPI % 2 == 0 || OPI<0) {
			System.err.println("Please restart the program and provide an odd positive integer");
			System.exit(-1);
		}
		else {
			while (count<OPI) {
				RAT.append(star);
				count++;
			}
			count = 0;
			
			StringBuilder help = new StringBuilder();
			help.append(RAT);
			//to draw the Right Angle Triangle
			System.out.println("Right-Angled Triangle:\n"+RAT);
			while (count<OPI) {
				RAT.setCharAt(count,space);
				System.out.println(RAT);
				count++;
			}
			OPI = (OPI/2); //to know how tall the isocelle Triangle need to be
			int OPI2 = OPI-1;
			//To Draw Iso Triangle
			
			int test = 1;
			System.out.println("Isosceles Triangle: ");
			for (int i2=0;i2<OPI;i2++) {
				for (int k = OPI2;k>=i2;k--) {
					System.out.print(" ");
				}
				for (int j=0;j<test;j++) {
					System.out.print("*");	
				}
				System.out.print("\n");
				test += 2;
			}
			System.out.println(help);
		}
	}
}
