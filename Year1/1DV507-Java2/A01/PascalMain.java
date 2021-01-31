package lg222sv_assign1;

import java.util.Scanner;

public class PascalMain {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
		System.out.print("Give me a positive integer: ");

		int nthrow = sc.nextInt();
		if (nthrow < 0) {
			throw new Exception("Please provide a positive integer");
		} else {
			sc.close();
			int[] printarray = pascalRow(nthrow);
			System.out.println("This is the " + nthrow + " th row of the Pascal's Triangle");
			for (int i = 0; i < printarray.length; i++) {
				System.out.print(printarray[i] + " ");
			}
		}
	}

	public static int[] pascalRow(int n) {
		int[] pascal = new int[n + 1];

		if (n == 0) {
			pascal[0] = 1;
			return pascal;
		} else if (n == 1) {
			pascal[0] = 1;
			pascal[pascal.length - 1] = 1;
			return pascal;
		} else {
			int[] pascalhelp = pascalRow(n - 1);
			pascal[0] = 1;
			pascal[pascal.length - 1] = 1;
			for (int i = 1; i <= pascal.length - 2; i++) {
				pascal[i] = pascalhelp[i] + pascalhelp[i - 1];
			}
			return pascal;
		}
	}
	
}
