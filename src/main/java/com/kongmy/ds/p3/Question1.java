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
public class Question1 {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        long startTime;
        long stopTime;

        startTime = System.nanoTime();
        int min = min(list);
        stopTime = System.nanoTime();
        System.out.printf("Elapsed time for min() was %d nanoseconds.\n", stopTime - startTime);

        startTime = System.nanoTime();
        int minLazy = minLazy(list);
        stopTime = System.nanoTime();
        System.out.printf("Elapsed time for minLazy() was %d nanoseconds.\n", stopTime - startTime);

        startTime = System.nanoTime();
        int minLambda = minLambda(list);
        stopTime = System.nanoTime();
        System.out.printf("Elapsed time for minLambda() was %d nanoseconds.\n", stopTime - startTime);

        System.out.printf("List: %s\n", list.toString());
        System.out.printf("Min : %d\n", min);
        System.out.printf("(Lazy) Min: %d\n", minLazy);
        System.out.printf("(Lambda) Min: %d\n", minLambda);
    }

    public static <E extends Comparable<E>> E min(ArrayList<E> list) {
        if (list.isEmpty()) {
            return null;
        } else {
            E min = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                if (min.compareTo(list.get(i)) > 0) {
                    min = list.get(i);
                }
            }
            return min;
        }
    }

    public static <E extends Comparable<E>> E minLazy(ArrayList<E> list) {
        if (list.isEmpty()) {
            return null;
        } else {
            ArrayList<E> newList = new ArrayList<>(list);
            Collections.sort(newList);
            return newList.get(0);
        }
    }

    public static <E extends Comparable<E>> E minLambda(ArrayList<E> list) {
        if (list.isEmpty()) {
            return null;
        } else {
            Comparator<E> comparator = getComparator();
            return list.stream().min(comparator).get();
        }
    }

    public static <E extends Comparable<E>> Comparator<E> getComparator() {
        return (o1, o2) -> o1.compareTo(o2);
    }

}
