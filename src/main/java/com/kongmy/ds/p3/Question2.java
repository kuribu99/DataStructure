/*
 */
package com.kongmy.ds.p3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Kong My
 */
public class Question2 {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        long startTime;
        long stopTime;

        startTime = System.nanoTime();
        int max = max(list);
        stopTime = System.nanoTime();
        System.out.printf("Elapsed time for max() was %d nanoseconds.\n", stopTime - startTime);

        startTime = System.nanoTime();
        int maxLazy = maxLazy(list);
        stopTime = System.nanoTime();
        System.out.printf("Elapsed time for maxLazy() was %d nanoseconds.\n", stopTime - startTime);

        startTime = System.nanoTime();
        int maxLambda = maxLambda(list);
        stopTime = System.nanoTime();
        System.out.printf("Elapsed time for maxLambda() was %d nanoseconds.\n", stopTime - startTime);

        System.out.printf("List: %s\n", list.toString());
        System.out.printf("Max : %d\n", max);
        System.out.printf("(Lazy) Max: %d\n", maxLazy);
        System.out.printf("(Lambda) Max: %d\n", maxLambda);
    }

    public static <E extends Comparable<E>> E max(ArrayList<E> list) {
        if (list.isEmpty()) {
            return null;
        } else {
            E max = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                if (max.compareTo(list.get(i)) < 0) {
                    max = list.get(i);
                }
            }
            return max;
        }
    }

    public static <E extends Comparable<E>> E maxLazy(ArrayList<E> list) {
        if (list.isEmpty()) {
            return null;
        } else {
            ArrayList<E> newList = new ArrayList<>(list);
            Collections.sort(newList);
            return newList.get(newList.size() - 1);
        }
    }

    public static <E extends Comparable<E>> E maxLambda(ArrayList<E> list) {
        if (list.isEmpty()) {
            return null;
        } else {
            Comparator<E> comparator = getComparator();
            return list.stream().max(comparator).get();
        }
    }

    public static <E extends Comparable<E>> Comparator<E> getComparator() {
        return (o1, o2) -> o1.compareTo(o2);
    }

}
