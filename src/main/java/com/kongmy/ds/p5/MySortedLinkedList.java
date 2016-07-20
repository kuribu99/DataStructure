/*
 */
package com.kongmy.ds.p5;

import java.util.Collection;

public class MySortedLinkedList<E extends Comparable<E>> extends MyLinkedList<E> {

    public MySortedLinkedList() {
        super();
    }

    public MySortedLinkedList(E[] array) {
        super();

        for (E object : array) {
            add(object);
        }
    }

    public MySortedLinkedList(Collection<? extends E> collection) {
        super();

        for (E object : collection) {
            add(object);
        }
    }

    @Override
    public boolean add(int index, E object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(E object) {
        Node<E> newNode = new Node<>(object);
        if (size == 0) {
            headNode = newNode;
            tailNode = newNode;
        } else {
            Node<E> currentNode = headNode;

            // Move until current node's element is large than object
            while (currentNode != null && currentNode.element.compareTo(object) < 0) {
                currentNode = currentNode.nextNode;
            }

            // Append after tail
            if (currentNode == null) {
                tailNode.nextNode = newNode;
                newNode.lastNode = tailNode;
                tailNode = newNode;

            } // Append at first
            else if (currentNode.equals(headNode)) {
                headNode = newNode;
                newNode.nextNode = currentNode;
                currentNode.lastNode = newNode;

            } // Append before other nodes
            else {
                newNode.nextNode = currentNode;
                newNode.lastNode = currentNode.lastNode;
                currentNode.lastNode = newNode;
                newNode.lastNode.nextNode = newNode;
            }

        }
        size++;
        return true;
    }

    @Override
    public void set(int index, E object) {
        throw new UnsupportedOperationException();
    }

}
