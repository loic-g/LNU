package lg222sv_assign4;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CountChar {

	private int NumberUpperCase;
	private int NumberLowerCase;
	private int NumberWhiteSpace;
	private int NumberOther;
	private String Path;
	private String ContentText;
	
	public CountChar(String path) {
		NumberLowerCase=0;
		NumberWhiteSpace=0;
		NumberUpperCase=0;
		NumberOther=0;
		Path = path; 
	}
	
	public void readFile() {
		StringBuilder text = new StringBuilder();
		try {
			Scanner sc = new Scanner(new File(Path));
			while(sc.hasNext()) {
				String help = sc.nextLine();
				text.append(help);
			}
			sc.close();
		}catch(IOException e) {e.printStackTrace();
		}
		ContentText = text.toString();
		for (int i=0;i<ContentText.length();i++) {
			char letter = ContentText.charAt(i);
			if (Character.isLowerCase(letter)) {NumberLowerCase++;}
			else if (Character.isUpperCase(letter)) {NumberUpperCase++;}
			else if (Character.isWhitespace(letter)) {NumberWhiteSpace++;}
			else {NumberOther++;}
		}
	}
	public int getLowerCase() {return NumberLowerCase;}
	public int getUpperCase() {return NumberUpperCase;}
	public int getWhiteSpaces() {return NumberWhiteSpace;}
	public int getOthers() {return NumberOther;}
	
	public static void main(String[]args) {
		String Path = "C:\\Users\\Loic PC\\Documents\\java_courses\\1DV506\\src\\lg222sv_assign4\\CountCHarrr.txt";
		CountChar counttchar = new CountChar(Path);
		counttchar.readFile();
		System.out.println("Number of upper case letters: "+counttchar.getUpperCase());
		System.out.println("Number of lower case letters: "+counttchar.getLowerCase());
		System.out.println("Number of \"whitespaces\": "+counttchar.getWhiteSpaces());
		System.out.println("Number of others: "+counttchar.getOthers());
		
		/*The reason why I do not get the same amount of whitespaces is because on the link from
		 * the assignment the text is formated in a certain way (20+ lines) but when
		 * I put on Notepad then there is less lines (5+). therefore, there is a number 
		 * of Whitespaces that are not included in my results
		 */
	}
	
	
	
	
	
	
}
