package lg222sv_assign1;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;


public class WarAndPeace {

	public static void main(String[] args) throws IOException {
		Scanner question = new Scanner(System.in);
		System.out.print("Please Provide the path to the file: ");
		String path = question.nextLine();
		// C:\\Users\\LoicPC\\Documents\\java_courses\\1DV507\\src\\lg222sv_assign1\\WarAndPeace.txt
		question.close();
		String text = readText(path);
		
		String[] words = text.split(" "); 
		System.out.println("Initial word count: " + words.length); // We found 577091
		
		
		Stream<String> stream = Arrays.stream(words);
		;
		long count = stream.map(s -> s.trim())
                .map(String::toLowerCase)
                .map(word -> word.replaceAll("[^a-zA-Z\'-]", ""))
                .filter(s -> s.length() > 0)
                .distinct()
                .count();	
		
		System.out.println("Amount of single words: " + count);
	}

	public static String readText(String path) {
		StringBuilder sb = new StringBuilder();
		try {

			Scanner sc = new Scanner(new File(path));
			while (sc.hasNextLine()) {
				String help = sc.nextLine();

				sb.append(help);
			}
			sc.close();
		} catch (IOException ioe) {
			System.out.println("The File Does not exist at the path " + path);
		}

		return sb.toString();
	}

}
