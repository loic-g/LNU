package lg222sv_assign2;

public class Reverse {

	public static void main(String[] args) {
		char[] text = {'!','y','s','a', 'E', ' ', 's', 'a', 'w', ' ', 
     			's', 'i', 'h', 'T'};
		
		System.out.println(text);
		int length = text.length; //get the length of the Array 
		int count=0;
		for (int i=0; count<length;i++) {
			char help = text[i]; //get the character from position 0 to lenght-1 and save it into the help variable
			text[i] = text[(length-1)-i]; //put the value of the last position, second last position and so on 
		
			text[(length-1)-i]=help;
			count+=2; //so that the program stop after reversing all the sentence 
		}
		System.out.println(text);//Print the Array
	}

}
