package lg222sv_assign2;

import java.util.Random;

public class TwoDices {

	public static void main(String[] args) {
		Random rd = new Random(); //Setup the random 
		int[] Dices = {0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		
		System.out.println("Frequency table (sum,count) for rolling two dices 10000 times");
		for (int i =0;i<10000;i++) {
			int nb1 = rd.nextInt(6)+1; //get number from 1 to 6
			int nb2 = rd.nextInt(6)+1;
			int number = nb1+nb2; //add them together
			
			for (int a=2;a<13;a++) {
				if (number==a) //check which number it is and add +1 into the variable 
					Dices[a]+=1;
			}
			
		}
		for (int i= 2;i<13;i++) {
			System.out.println(i+"\t"+Dices[i]);
		}
		

	}

}
