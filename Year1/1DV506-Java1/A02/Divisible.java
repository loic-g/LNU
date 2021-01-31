package lg222sv_assign2;

public class Divisible {

	public static void main(String[] args) {
		int count=0; //setup the count variable 
		
		//use the loop to check every number 
		for (int number=100; number<=200; number++) {//Setup loop
			//use the remainder if divide by 4 or 5 and check if it is equal to 0. if it is then it is divisible by 4 or 5
			if ((number%5==0||number%4==0)&& !(number%5==0 && number%4==0)) {//Works only if the number is divisible by 4 or 5 but not both 
				System.out.print(number+" ");
				count++;
			}
			if (count==10) { //every ten number start to the line below 
				System.out.print("\n");
				count = 0;
			}
		}

	}

}
