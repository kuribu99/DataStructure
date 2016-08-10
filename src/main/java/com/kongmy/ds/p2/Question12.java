/*
 */
package com.kongmy.ds.p2;

/**
 *
 * @author Kong My
 */
public class Question12 {

    public static void main(String[] args) {
        for (int n = 1; n < 10; n++) {
            System.out.printf("Sum of square of %d = %d\n", n, SumOfSquare(n));
        }
    }

    public static int SumOfSquare(int n) {
        if (n == 1) {
            return 1;
        }
        else {
            return n * n + SumOfSquare(n - 1);
        }
    }

}
