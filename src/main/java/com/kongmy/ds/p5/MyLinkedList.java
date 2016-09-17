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

            Node<E> newNode;
            for (int i = 1; i < array.length; i++) {
                newNode = new Node<>(array[i]);
                currentNode.nextNode = newNode;
                newNode.previousNode = currentNode;
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
    public boolean add(int index, E object) {
        ensureValidIndex(index);

        Node<E> newNode = new Node(object);
        if (index == 0) {
            headNode.previousNode = newNode;
            headNode = newNode;
        }
        else {
            Node<E> currentNode = getNode(index);
            newNode.nextNode = currentNode;
            newNode.previousNode = currentNode.previousNode;
            newNode.nextNode.previousNode = newNode;
            newNode.previousNode.nextNode = newNode;
        }

        size++;
        return true;
    }

    @Override
    public boolean add(E object) {
        Node<E> newNode = new Node(object);
        if (size == 0) {
            headNode = newNode;
            tailNode = newNode;
        }
        else {
            newNode.previousNode = tailNode;
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

    @Override
    public void set(int index, E object) {
        Node<E> currentNode = getNode(index);
        currentNode.element = object;
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
            currentNode = currentNode.previousNode;
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
            headNode.previousNode = null;
        }

        // Update last if is last
        else if (index == size - 1) {
            tailNode = tailNode.previousNode;
            tailNode.nextNode = null;
        }

        // Simply link next and last node
        else {
            removedNode.previousNode.nextNode = removedNode.nextNode;
            removedNode.nextNode.previousNode = removedNode.previousNode;
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
            }
            else {
                current++;
            }
            return iteratorArray[current];
        }
    }

    protected class Node<E> {

        protected E element;
        protected Node<E> nextNode;
        protected Node<E> previousNode;

        public Node(E element) {
            this.element = element;
            this.nextNode = null;
            this.previousNode = null;
        }

    }

}
