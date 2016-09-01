/*
 */
package com.kongmy.ds.p11;

import java.util.Arrays;

/**
 *
 * @author Kong My
 */
public final class SortingAlgorithms {

    private static int[] subList(int[] arr, int startInclusive, int endExclusive) {
        int[] subList = new int[endExclusive - startInclusive];

        for (int i = 0; i < subList.length; i++) {
            subList[i] = arr[startInclusive++];
        }

        return subList;
    }

    private static void printDepth(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("     ");
        }
    }

    private static void swap(int[] arr, int leftIndex, int rightIndex) {
        int temp = arr[leftIndex];
        arr[leftIndex] = arr[rightIndex];
        arr[rightIndex] = temp;
    }

    private static String subListToString(int[] arr, int startInclusive, int endInclusive) {
        StringBuilder builder = new StringBuilder();

        // Opening bracket
        builder.append("[");

        for (int i = startInclusive; i <= endInclusive; i++) {
            builder.append(arr[i]).append(",");
        }

        // Remove tailing comma if exists
        if (builder.charAt(builder.length() - 1) == ',') {
            builder.deleteCharAt(builder.length() - 1);
        }

        // Closing bracket
        builder.append("]");

        return builder.toString();
    }

    public static class MergeSort {

        public static void sort(int[] arr) {
            System.out.println("Before: " + Arrays.toString(arr));
            splitAndSort(arr, 0);
            System.out.println("After: " + Arrays.toString(arr));
        }

        private static void splitAndSort(int[] arr, int depth) {
            int size = arr.length;

            // Split and sort only if array has more than 1 value
            if (size > 1) {
                printDepth(depth);
                System.out.println("Sorting " + Arrays.toString(arr));

                // Calculate where should right sublist start
                int rightStart;

                if (size % 2 == 0) {
                    rightStart = size / 2;
                }
                else {
                    rightStart = (size + 1) / 2;
                }

                // Split and sort sublist
                int[] leftSubList = subList(arr, 0, rightStart);
                int[] rightSubList = subList(arr, rightStart, size);

                printDepth(depth);
                System.out.printf(
                        "Split %s to %s and %s\n",
                        Arrays.toString(arr),
                        Arrays.toString(leftSubList),
                        Arrays.toString(rightSubList));

                splitAndSort(leftSubList, depth + 1);
                splitAndSort(rightSubList, depth + 1);

                // Merge back sublists
                int index = 0;
                int currentLeftIndex = 0;
                int currentRightIndex = 0;

                // Merge until one list is empty
                while (currentLeftIndex < leftSubList.length && currentRightIndex < rightSubList.length) {

                    // Replace arr[index] with smallest from both sublist
                    if (leftSubList[currentLeftIndex] <= rightSubList[currentRightIndex]) {
                        arr[index++] = leftSubList[currentLeftIndex++];
                    }
                    else {
                        arr[index++] = rightSubList[currentRightIndex++];
                    }
                }

                // Add back remaining either from left or from right sublist
                while (currentLeftIndex < leftSubList.length) {
                    arr[index++] = leftSubList[currentLeftIndex++];
                }

                while (currentRightIndex < rightSubList.length) {
                    arr[index++] = rightSubList[currentRightIndex++];
                }

                // Done
                printDepth(depth);
                System.out.printf(
                        "Combined %s and %s to %s\n",
                        Arrays.toString(leftSubList),
                        Arrays.toString(rightSubList),
                        Arrays.toString(arr));
            }

            else {
                printDepth(depth);
                System.out.println("No need to sort " + Arrays.toString(arr));
            }
        }

    }

    public static class QuickSort {

        public static void sort(int[] arr) {
            System.out.println("Before: " + Arrays.toString(arr));
            splitAndSort(arr, 0, 0, arr.length - 1);
            System.out.println("After: " + Arrays.toString(arr));
        }

        private static void splitAndSort(int[] arr, int depth, int start, int end) {
            int size = end - start + 1;

            if (size > 1) {
                int pivot = arr[start];
                int low = start + 1;
                int high = end;

                printDepth(depth);
                System.out.printf("Sorting %s based on pivot %d\n", subListToString(arr, start + 1, end), pivot);

                // Loop and  sort until all values after high is greater than pivot
                // And all values before low are smaller than pivot
                while (low < high) {
                    // Search forward for value higher than pivot
                    // But must stop before high
                    while (pivot >= arr[low] && low < high) {
                        low++;
                    }

                    // Search forward for value greater than pivot
                    // But must stop after low
                    while (pivot < arr[high] && low < high) {
                        high--;
                    }

                    // Swap low and high if needed
                    if (arr[low] > arr[high]) {
                        swap(arr, low, high);
                    }
                }

                // At this point, high is same position with low
                // And all values are sorted before and after high/low
                // So we need to move high to a position where pivot is lower than it
                // And we use it as center
                while (arr[high] > pivot && high > start) {
                    high--;
                }

                // Swap if needed
                if (arr[high] < pivot) {
                    swap(arr, start, high);
                }

                printDepth(depth);
                if (high == start) {
                    System.out.printf(
                            "Splitting to %s and %s with center %d\n",
                            "[]",
                            subListToString(arr, high + 1, end),
                            arr[high]);
                }
                else if (high == end) {
                    System.out.printf(
                            "Splitting to %s and %s with center %d\n",
                            subListToString(arr, start, high - 1),
                            "[]",
                            arr[high]);
                }
                else {
                    System.out.printf(
                            "Splitting to %s and %s with center %d\n",
                            subListToString(arr, start, high - 1),
                            subListToString(arr, high + 1, end),
                            arr[high]);
                }

                // So now we got high as center, apply sorting to both parts
                splitAndSort(arr, depth + 1, start, high - 1);
                splitAndSort(arr, depth + 1, high + 1, end);
                
                printDepth(depth);
                if (high == start) {
                    System.out.printf(
                            "Combining %s and %s with center %d\n",
                            "[]",
                            subListToString(arr, high + 1, end),
                            arr[high]);
                }
                else if (high == end) {
                    System.out.printf(
                            "Combining %s and %s with center %d\n",
                            subListToString(arr, start, high - 1),
                            "[]",
                            arr[high]);
                }
                else {
                    System.out.printf(
                            "Combining %s and %s with center %d\n",
                            subListToString(arr, start, high - 1),
                            subListToString(arr, high + 1, end),
                            arr[high]);
                }

            }
            // No need to sort empty or 1 item
            else {
                printDepth(depth);
                System.out.println("No need to sort " + subListToString(arr, start, end));
            }
        }

    }

}
