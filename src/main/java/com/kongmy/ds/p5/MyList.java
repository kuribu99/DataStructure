/*
 */
package com.kongmy.ds.p5;

/**
 *
 * @author Kong My
 */
public interface MyList<E> extends Iterable<E> {

    /**
     * Adds the elements in otherList to this list. Returns true if this list
     * changed as a result of the call
     */
    public boolean addAll(MyList<E> otherList);

    /**
     * Removes all the elements in otherList from this list. Returns true if
     * this list changed as a result of the call
     */
    public boolean removeAll(MyList<E> otherList);

    /**
     * Retains the elements in this list that are also in otherList. Returns
     * true if this list changed as a result of the call
     */
    public boolean retainAll(MyList<E> otherList);

}
