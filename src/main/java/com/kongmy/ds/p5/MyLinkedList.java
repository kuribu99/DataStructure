/*
 */
package com.kongmy.ds.p5;

import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author Kong My
 */
public class MyLinkedList<E> extends MyAbstractList<E> {

    protected Node<E> headNode;
    protected Node<E> tailNode;
    protected int size;

    public MyLinkedList() {
        headNode = null;
        tailNode = null;
        size = 0;
    }

    public MyLinkedList(E[] array) {
        this();

        if (array.length > 0) {
            Node<E> currentNode = new Node<>(array[0]);
            headNode = currentNode;

            Node<E> newNode = null;
            for (int i = 1; i < array.length; i++) {
                newNode = new Node<>(array[i]);
                currentNode.nextNode = newNode;
                newNode.lastNode = currentNode;
                currentNode = newNode;
            }

            tailNode = currentNode;
        }
        size = array.length;
    }

    public MyLinkedList(Collection<? extends E> collection) {
        this((E[]) collection.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(E object) {
        Node<E> newNode = new Node(object);
        if (size == 0) {
            headNode = newNode;
            tailNode = newNode;
        } else {
            newNode.lastNode = tailNode;
            tailNode.nextNode = newNode;
            tailNode = newNode;
        }
        size++;
        return true;
    }

    @Override
    public E get(int index) {
        return getNode(index).element;
    }

    protected Node<E> getNode(int index) {
        ensureValidIndex(index);

        Node<E> currentNode = headNode;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.nextNode;
        }

        return currentNode;
    }

    public void set(int index, E object) {
        Node<E> currentNode = getNode(index);
        Node<E> newNode = new Node<>(object);

        // Set to head if it is first
        if (index == 0) {
            newNode.nextNode = headNode.nextNode;
            newNode.nextNode.lastNode = newNode;
            headNode = newNode;

        } // Set to tail if last
        else if (index == size - 1) {
            newNode.lastNode = tailNode.lastNode;
            newNode.lastNode.nextNode = newNode;
            tailNode = newNode;

        } // Replace current node
        else {
            newNode.nextNode = currentNode.nextNode;
            newNode.lastNode = currentNode.lastNode;
            newNode.nextNode.lastNode = newNode;
            newNode.lastNode.nextNode = newNode;
        }
    }

    @Override
    public int indexOf(E object) {
        int index = 0;
        Node<E> currentNode = headNode;

        // Loop until end
        while (index < size) {
            if (currentNode.element.equals(object)) {
                return index;
            }
            currentNode = currentNode.nextNode;
            index++;
        }

        return -1;
    }

    public int lastIndexOf(E object) {
        int index = size - 1;
        Node<E> currentNode = tailNode;

        // Loop until first
        while (index >= 0) {
            if (currentNode.element.equals(object)) {
                break;
            }
            currentNode = currentNode.lastNode;
            index--;
        }

        // Must be -1
        return index;
    }

    @Override
    public Object remove(int index) {
        Node<E> removedNode = getNode(index);

        // Update head if is first
        if (index == 0) {
            headNode = removedNode.nextNode;
            headNode.lastNode = null;

        } // Update last if is last
        else if (index == size - 1) {
            tailNode = tailNode.lastNode;
            tailNode.nextNode = null;

        } // Simply link next and last node
        else {
            removedNode.lastNode.nextNode = removedNode.nextNode;
            removedNode.nextNode.lastNode = removedNode.lastNode;
        }

        size--;
        return removedNode.element;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    protected class LinkedListIterator implements Iterator {

        protected final E[] iteratorArray;
        protected int current;

        public LinkedListIterator() {
            iteratorArray = (E[]) new Object[size];

            Node<E> currentNode = headNode;
            for (int i = 0; i < size; i++) {
                iteratorArray[i] = currentNode.element;
                currentNode = currentNode.nextNode;
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
            } else {
                current++;
            }
            return iteratorArray[current];
        }
    }

    protected class Node<E> {

        protected final E element;
        protected Node<E> nextNode;
        protected Node<E> lastNode;

        public Node(E element) {
            this.element = element;
            this.nextNode = null;
            this.lastNode = null;
        }

    }

}
