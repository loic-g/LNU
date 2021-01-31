package lg222sv_assign3;

import java.util.Scanner;

public class EuclideanMain {
    public static void main(String[] args) {
        System.out.println("Please provide 2 positive integers");
        Scanner sc = new Scanner(System.in);
        System.out.print("Integer 1: ");
        final int a = sc.nextInt();
        System.out.print("Integer 2: ");
        final int b = sc.nextInt();
        if (a < 0 && b < 0) {
            int multiplier = 0;
            if (a > b) {
                for (int i = 1; i < b; i++) {
                    if ((a % i == 0) && (b % i == 0)) {
                        multiplier = i;
                    }
                }
            } else {
                for (int i = 1; i < a; i++) {
                    if ((a % i == 0) && (b % i == 0)) {
                        multiplier = i;
                    }
                }
            }
            System.out.println("GCD(" + a + "," + b + ") = " + multiplier);
        }else
            System.out.println("Please enter positive integers");
    }
}
