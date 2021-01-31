package lg222sv_assign2;

import java.util.Scanner;

public class Palindrome {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Provide a sentence: ");
		String text = sc.nextLine();		
		sc.close();
		int length=text.length();
		
		StringBuilder sb = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		
		//to write the text in reverse with only lowercase letter
		for (int i=0;i<length;i++) {
			char a = text.charAt((length-1)-i);
			 
			if (Character.isLetter(a)) { //Check if it is letter and if it is then put to lower case
				a =Character.toLowerCase(a);
				sb.append(a);
			}
		}
		//To get the initial text with only lowercase letter
		for (int i=0;i<length;i++) {
			char b = text.charAt(i);
			 
			if (Character.isLetter(b)) {
				b =Character.toLowerCase(b);
				sb2.append(b);
			}
		}
		
		String t1 = sb.toString(); //change it to String to check if both content is the same 
		String t2 = sb2.toString();
		
		if (t1.equals(t2)) {
			System.out.println("The sentence is a palindrome! Good Job !");
		}
			else {
				System.out.println("This is not a palindrome! Please try again");
			}
	}

}
