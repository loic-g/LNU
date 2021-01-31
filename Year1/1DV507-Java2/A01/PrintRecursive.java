package lg222sv_assign1;

public class PrintRecursive {

	public static void main(String[] args) {
		String str = "Hello Everyone!";

		print(str, 0);
		System.out.println("\n"); // Line break
		printReverse(str, 0);

	}

	public static void print(String text, int pos) {
		if (text.length() == pos) {
			// Do Nothing
		} else {
			System.out.print(text.charAt(pos));
			print(text, pos + 1);
		}
	}

	public static void printReverse(String text, int pos) {
		if ((text.length()) == pos) {

		} else {
			System.out.print(text.charAt(text.length()- pos-1));
			printReverse(text, pos + 1);
		}
	}

}
