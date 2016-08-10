/*
 */
package com.kongmy.ds.p7;

/**
 *
 * @author Kong My
 */
public interface Tree<E> extends Iterable {

    boolean search(E object);

    boolean insert(E object);

    boolean delete(E object);

    void inOrder();

    void preOrder();

    void postOrder();

    int getSize();

    boolean isEmpty();

    void clear();

}
