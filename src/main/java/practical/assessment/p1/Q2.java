package practical.assessment.p1;

import java.util.Scanner;

/**
 *
 * @author Kong My
 */
public class Q2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter an integer: ");
        String input = scanner.nextLine();
        try {
            // Try parse to validate that input is integer
            int number = Integer.parseInt(input);
            System.out.printf("decimalCommas(%d) => %s\n", number, decimalCommas(number));
        } catch (NumberFormatException e) {
            System.out.println("\nError: Invalid input.");
        }
    }

    public static String decimalCommas(int n) {
        if (Math.abs(n) < 1000) {
            return String.valueOf(n);
        } else {
            return String.format("%s,%03d", decimalCommas(n / 1000), Math.abs(n) % 1000);
        }
    }

}
