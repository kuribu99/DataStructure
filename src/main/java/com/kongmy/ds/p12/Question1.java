/*
 */
package com.kongmy.ds.p12;

import static com.kongmy.ds.p11.Question1.generateIntegers;
import static com.kongmy.ds.p11.SortingAlgorithms.QuickSort;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Kong My
 */
public class Question1 {

    public static void main(String[] args) {
        int[] arr = generateIntegers(20);
        QuickSort.sort(arr);

        System.out.println(Arrays.toString(arr));

        Random rand = new Random();
        int search;
        int index;
        for (int i = 0; i < 20; i++) {
            search = rand.nextInt(101);
            System.out.printf("Searching for %d in array, ", search);
            index = binarySearch(arr, search);

            if (index >= 0) {
                System.out.println("found at index " + index);
            }
            else {
                System.out.println("not found");
            }
        }
    }

    private static int binarySearch(int[] arr, int search) {
        int start = 0;
        int end = arr.length - 1;
        int mid = (start + end + 1) / 2;
        int delta;

        while (mid != start && mid != end) {

            delta = arr[mid] - search;

            // Found
            if (delta == 0) {
                return mid;
            }
            // Mid is smaller than search target
            // Look at RHS
            else if (delta < 0) {
                start = mid + 1;
            }
            // Mid is larger than search target
            // Look at LHS
            else if (delta > 0) {
                end = mid - 1;
            }
            
            // Update mid
            mid = (start + end + 1) / 2;
        }

        return -1;
    }

}
