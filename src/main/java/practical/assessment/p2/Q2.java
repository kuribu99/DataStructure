package practical.assessment.p2;

import java.util.Scanner;

/**
 *
 * @author Kong My
 */
public class Q2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter an integer from 0 to 99999: ");
        String input = scanner.nextLine();
        try {
            // Try parse to validate that input is integer
            int number = Integer.parseInt(input);
            if (number < 0 || number > 99999) {
                System.out.println("\nError. Input must be from 0 to 99999");
            }
            else {
                recursivePrint(number);
            }
        }
        catch (NumberFormatException e) {
            System.out.println("\nError: Invalid input.");
        }
    }

    public static void recursivePrint(int input) {
        recursivePrint(input, 10000, 10000);
    }

    public static void recursivePrint(int input, int currentFactor, int loopFactor) {
        if (loopFactor == 0) {
            return;
        }
        else if (currentFactor == 0) {
            System.out.println();
            recursivePrint(input % loopFactor, loopFactor / 10, loopFactor / 10);
        }
        else {
            System.out.printf("%d   ", (input / currentFactor) % 10);
            recursivePrint(input, currentFactor / 10, loopFactor);
        }
    }

}
