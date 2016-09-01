/*
 */
package com.kongmy.ds.p11;

import static com.kongmy.ds.p11.Question1.*;
import java.util.Arrays;

/**
 *
 * @author Kong My
 */
public class Question2 {

    public static void main(String[] args) {
        int[] arr = generateIntegers(20);

        SortingAlgorithms.QuickSort.sort(Arrays.copyOf(arr, 20));
        System.out.println("\n\n\n");
        SortingAlgorithms.MergeSort.sort(Arrays.copyOf(arr, 20));
    }

}
