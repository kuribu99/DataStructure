/*
 */
package com.kongmy.ds.p2;

/**
 *
 * @author Kong My
 */
public class Question10 {

    public static void main(String[] args) {
        PrintToZero(10);
    }

    public static void PrintToZero(int n) {
        System.out.printf("%d ", n);
        if (n > 0) {
            PrintToZero(n - 1);
        }
    }

}
