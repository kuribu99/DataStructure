/*
 */
package com.kongmy.ds.p11;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Kong My
 */
public class Question1 {

    public static void main(String[] args) {
        int[] arr = generateIntegers(20);
        printArray(arr);
    }
    
    public static int[] generateIntegers(int count) {
        int[] arr = new int[count];
        Random rand = new Random();
        
        for(int i = 0; i < count; i++) {
            arr[i] = rand.nextInt(101);
        }
        
        return arr;
    }

    private static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

}
