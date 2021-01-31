package lg222sv_assign3;

import java.util.Scanner;


public class SweID {
	
	
	//Ex 1 
	private static String toString(int[]n) {
		int length=n.length;
		String NiceLooking="";
		for (int i =0;i<length;i++) {
			NiceLooking+=n[i];
		}
		return NiceLooking;
	}
	
	private static int[] getFirstPart(String sweID) {
		int[]FirstPart =new int[6];
		for (int i =0;i<6;i++) {
			FirstPart[i]=Character.getNumericValue(sweID.charAt(i));
		}
		return FirstPart;
	}
	
	private static int[] getSecondPart(String sweID) {
		int[]Second =new int[4];
		int count=0;
		for (int i =7;i<11;i++) {
			Second[count]=Character.getNumericValue(sweID.charAt(i));
			count++;
			
		}
		return Second;
	}
	//Ex 2 
	private static boolean isFemaleNumber(String sweID) {
		boolean FM=true;
		int help=Character.getNumericValue(sweID.charAt(sweID.length()-1));
		if (help%2==0) {
			FM = true;
		}
		else
			FM=false;
		return FM;
	}
	//Ex 3
	private static boolean areEqual(String id1, String id2) {
		boolean Equal = false;
		
		if (id1.equals(id2)){
			Equal = true;
		}
		else
			Equal = false;
		return Equal;
	}
	//Ex 4
	private static boolean isCorrect(String sweID) {
		boolean Correct = false;
		boolean Year=false;
		boolean Month=false;
		boolean date=false;
	
		int[] Months = {31,28,31,30,31,30,31,31,30,31,30,31};
		int[] num =getFirstPart(sweID);
		int[] numl =getSecondPart(sweID);
		int HelpY = (num[0]*10)+num[1];
		int HelpM = (num[2]*10)+num[3];
		int HelpD = (num[4]*10)+num[5];
		//checking the year
		if (((HelpY)>=0) && ((HelpY)<=99)) {
			Year = true;
		}
		else 
			Year = false;
		
		//checking the Month and day 
		
		if (((HelpM)>0) && ((HelpM)<=12)) {
			Month = true;
			if ((HelpD<Months[HelpM-1]) && (HelpD>0)) {
				date=true;
			}
			else
				date = false;
		}
		else 
			Month = false;
		//Checking for the checksum 
		int HelpCheck=0;
		// for the first part 
		for (int i=0;i<6;i+=2) {
			if ((2*num[i])>9) {
				HelpCheck=((2*num[i])/10 )+ ((2*num[i])%10);
			}
			else
				HelpCheck+=2*num[i];
			
			HelpCheck+=num[i+1] ;
		}
		//For second part
		for (int i=0;i<3;i++) {
			if(i%2==0) {
				if ((2*numl[i])>9) {
					HelpCheck=((2*numl[i])/10 )+((2*numl[i])%10);
				}
				else
					HelpCheck+=2*numl[i];
			}
			else
				HelpCheck+=numl[i];
		}
		HelpCheck%=10;
		HelpCheck=10-HelpCheck;
		
		if ((Year==true) && (Month==true)) {
			if (date==true && HelpCheck==numl[3]) {
				Correct = true;
			}
			else 
				Correct = false;
		}
		else 
			Correct = false;
		
		return Correct;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Please give your ID number in the form YYMMDD-NNNN : ");
		String sweID = sc.nextLine();
		String id1 = "980413-1911";
		String id2 = "980413-1911";
		sc.close();
		
		System.out.println("\nEx 1: \nThis is the first part of the ID number: "+toString(getFirstPart(sweID)));
		System.out.println("This is the Second part of the ID number: "+toString(getSecondPart(sweID)));
		System.out.println("\nEx 2: \nDoes this personal identity number belongs to a woman? "+isFemaleNumber(sweID));
		System.out.println("\nEx 3: \nAre the 2 ID numbers identical? "+areEqual(id1,id2));
		System.out.println("\nEx 4: \nIs the ID number correct? "+isCorrect(sweID));
	}

}
