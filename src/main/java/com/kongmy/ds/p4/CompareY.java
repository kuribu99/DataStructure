/*
 */
package com.kongmy.ds.p4;

import java.util.Comparator;

/**
 *
 * @author Kong My
 */
public class CompareY implements Comparator<Point> {

    @Override
    public int compare(Point o1, Point o2) {
        int yResult = Double.compare(o1.getY(), o2.getY());
        return yResult == 0
                ? Double.compare(o1.getX(), o2.getX())
                : yResult;
    }

}
