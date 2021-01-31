package lg222sv_assign3;

import java.text.DecimalFormat;
import java.util.Scanner;

public class DrunkenWalk {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter the size: ");
		int Size = sc.nextInt();
		System.out.print("Enter the number of steps: ");
		int nbsteps =sc.nextInt();
		System.out.print("Enter the number of walks: ");
		int nbwalk=sc.nextInt();
		sc.close();
		if (nbwalk<0) {
			System.out.println("PLEASE PROVIDE AN POSITIVE INTEGER");
			System.exit(-1);
		}
		int FellCount=0;
		for (int i=0;i<nbwalk;i++) {
			RandomWalk RDW = new RandomWalk(nbsteps,Size);
			RDW.walk();
			if (RDW.inBounds()==false) {
				FellCount++;
			}
		}
		double Percentage = ((double)FellCount/(double)nbwalk)*100;
		DecimalFormat df = new DecimalFormat("0.##");
		String Perc = df.format(Percentage);
		System.out.println("Out of "+nbwalk+" drunk people, "+FellCount+" ("+Perc+"%) fell into the water.");
	}

}
