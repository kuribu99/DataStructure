/*
 */
package com.kongmy.ds.p4;

import java.util.LinkedList;

/**
 *
 * @author Kong My
 */
public class Question4 {

    private static final int NUMBER_OF_ELEMENT = 100000;

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        long startTime, startM;
        long endTime, endM;
        int temp;

        // Adding 5mil integers
        for (int i = 0; i < NUMBER_OF_ELEMENT; i++) {
            list.add(i);
        }

        startTime = System.nanoTime();
        startM = System.currentTimeMillis();
        for (int i : list) {
            temp = i;
        }
        endTime = System.nanoTime();
        endM = System.currentTimeMillis();

        System.out.printf("Elapsed time for using for-each is %s nanoseconds.\n",
                decimalCommas(endTime - startTime));
        System.out.printf("Elapsed time for using for-each is %s milliseconds.\n",
                decimalCommas(endM - startM));

        startTime = System.nanoTime();
        startM = System.currentTimeMillis();
        for (int i = 0; i < NUMBER_OF_ELEMENT; i++) {
            temp = list.get(i);
        }
        endTime = System.nanoTime();
        endM = System.currentTimeMillis();

        System.out.printf("Elapsed time for using get() is %s nanoseconds.\n",
                decimalCommas(endTime - startTime));
        System.out.printf("Elapsed time for using get() is %s milliseconds.\n",
                decimalCommas(endM - startM));
    }

    // Imported from practical assessment
    public static String decimalCommas(long n) {
        if (Math.abs(n) < 1000) {
            return String.valueOf(n);
        } else {
            return String.format("%s,%03d", decimalCommas(n / 1000), Math.abs(n) % 1000);
        }
    }

}
