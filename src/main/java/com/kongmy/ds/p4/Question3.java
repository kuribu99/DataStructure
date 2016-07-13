/*
 */
package com.kongmy.ds.p4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Kong My
 */
public class Question3 {

    public static void main(String[] args) {
        Random rand = new Random();
        ArrayList<Point> list = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            list.add(new Point(rand.nextInt(100), rand.nextInt(100)));
        }

        // Sort by x, then y
        Collections.sort(list);
        for (Point p : list) {
            System.out.println(p.toString());
        }

        // Sort by y, then x
        Collections.sort(list, new CompareY());
        for (Point p : list) {
            System.out.println(p.toString());
        }
    }

}
