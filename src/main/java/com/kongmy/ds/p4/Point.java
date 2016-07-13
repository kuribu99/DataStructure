/*
 */
package com.kongmy.ds.p4;

/**
 *
 * @author Kong My
 */
public class Point implements Comparable<Point> {

    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public int compareTo(Point o) {
        int xResult = Double.compare(x, o.x);
        return xResult == 0
                ? Double.compare(y, o.y)
                : xResult;
    }

    @Override
    public String toString() {
        return String.format("{ x: %f, y:%f }", x, y);
    }

}
