/*
 */
package com.kongmy.ds.p2;

/**
 *
 * @author Kong My
 */
public class Question13 {

    public static void main(String[] args) {
        for (int n = 1; n < 10; n++) {
            System.out.printf("Pow(%d, %d) = %.2f\n", 2, n, pow(2, n));
        }
    }

    public static double pow(double base, int exponent) {
        if (base == 1 || exponent == 0) {
            return 1;
        }
        return base * pow(base, exponent - 1);
    }

}
