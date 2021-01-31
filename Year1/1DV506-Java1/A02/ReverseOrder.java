package lg222sv_assign2;
import java.util.*;
public class ReverseOrder {

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();//setup a new ArrayList
		System.out.println("Enter positive integers. End by giving a negative integer.");
		Scanner sc = new Scanner(System.in);
		int integer =0;
		int count=0;
		//To get all the integers until 
		do {
			System.out.print("Integer "+(count+1)+": ");
			integer = sc.nextInt();
			
			if (integer>=0) {
				list.add(integer); //add into the ArrayList
				count++;
			}
			else 
				break;
		}while(integer>=0);
		
		int length=list.size();
		System.out.println("\nNumber of positive integer: "+count);
		System.out.print("In reverse order: ");
		//Print in the reverse order
		for (int i=0;i<count;i++) {
			System.out.print(list.get((length-1)-i));
			
			if (((length-1)-i)>0) {
				System.out.print(", ");
			}
			else 
				System.out.print(" ");
		}
		
		sc.close();
	}

}
