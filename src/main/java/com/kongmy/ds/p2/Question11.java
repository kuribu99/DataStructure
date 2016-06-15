/*
 */
package com.kongmy.ds.p2;

/**
 *
 * @author Kong My
 */
public class Question11 {

    public static void main(String[] args) {
        PrintToN(10);
    }

    public static void PrintToN(int n) {
        if (n > 0) {
            PrintToN(n - 1);
        }
        System.out.printf("%d ", n);
    }

}
