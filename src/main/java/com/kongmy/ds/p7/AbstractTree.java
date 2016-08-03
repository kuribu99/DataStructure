/*
 */
package com.kongmy.ds.p7;

import java.util.Iterator;

/**
 *
 * @author Kong My
 */
public abstract class AbstractTree<E> implements Tree<E> {
    
    protected int size;
    
    protected AbstractTree() {
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

}
