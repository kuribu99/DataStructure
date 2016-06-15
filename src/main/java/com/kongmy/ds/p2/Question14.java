/*
 */
package com.kongmy.ds.p2;

/**
 *
 * @author Kong My
 */
public class Question14 {

    public static void main(String[] args) {
        int n = 9;
        System.out.printf("Decimal = %d\n", n);
        System.out.print("Binary = ");
        binaryPrint(n);
        System.out.println();
    }

    public static void binaryPrint(int x) {
        if (x / 2 > 0) {
            binaryPrint(x / 2);
        }
        System.out.print(x % 2);
    }

}
