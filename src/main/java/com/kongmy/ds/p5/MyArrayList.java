/*
 */
package com.kongmy.ds.p5;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author Kong My
 */
public class MyArrayList<E> extends MyAbstractList<E> {

    private static final int INITIAL_SIZE = 1;
    private static final int FACTOR = 2;

    protected E[] array;
    protected int size;

    public MyArrayList() {
        array = (E[]) new Object[INITIAL_SIZE];
    }

    public MyArrayList(E[] array) {
        this();

        for (E object : array) {
            add(object);
        }
    }

    public MyArrayList(Collection<? extends E> collection) {
        this();

        for (E object : array) {
            add(object);
        }
    }

    protected void ensureCapacity(int newSize) {
        if (newSize > size) {
            array = Arrays.copyOf(array, array.length * FACTOR);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(E object) {
        ensureCapacity(size + 1);
        array[size++] = object;
        return true;
    }

    public boolean add(int index, E object) {
        ensureValidIndex(index);
        ensureCapacity(size + 1);

        // Push objects back by 1
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }

        array[index] = object;
        size++;
        return true;
    }

    @Override
    public E get(int index) {
        ensureValidIndex(index);
        return array[index];
    }

    @Override
    public void set(int index, E object) {
        ensureValidIndex(index);
        array[index] = object;
    }

    @Override
    public int indexOf(E object) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(object)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Object remove(int index) {
        Object removedObject = get(index);

        // Overlap the object from behind
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return removedObject;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    protected class ArrayListIterator implements Iterator {

        protected final E[] iteratorArray;
        protected int current;

        public ArrayListIterator() {
            iteratorArray = (E[]) new Object[size()];
            for (int i = 0; i < size(); i++) {
                iteratorArray[i] = get(i);
            }
            current = -1;
        }

        @Override
        public boolean hasNext() {
            return current < iteratorArray.length - 1;
        }

        @Override
        public Object next() {
            if (current < 0) {
                current = 0;
            }
            else {
                current++;
            }
            return iteratorArray[current];
        }
    }

}
