/*
 */
package com.kongmy.ds.p7;

import java.util.Iterator;

/**
 *
 * @author Kong My
 */
public class BinaryTree<E> extends AbstractTree<Comparable<E>> {

    private TreeNode<Comparable<E>> rootNode;

    @Override
    public boolean search(Comparable<E> object) {
        for (Iterator it = new InOrderIterator(); it.hasNext();) {
            if (object.equals(it.next())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean insert(Comparable<E> object) {
        if (rootNode == null) {
            rootNode = new TreeNode<>(object);;
        }
        else {
            insert(rootNode, object);
        }
        size++;
        return true;
    }

    protected boolean insert(TreeNode<Comparable<E>> currentNode, Comparable<E> newElement) {
        // If smaller than current, insert to left
        if (newElement.compareTo((E) currentNode.element) < 0) {
            // Try to insert to left if empty
            if (currentNode.leftNode == null) {
                currentNode.leftNode = new TreeNode<>(newElement);
                return true;
            } // Recursively insert based on left node
            else {
                return insert(currentNode.leftNode, newElement);
            }

        }
        else {
            // Try to insert to right if empty
            if (currentNode.rightNode == null) {
                currentNode.rightNode = new TreeNode<>(newElement);
                return true;
            } // Recursively insert based on right node
            else {
                return insert(currentNode.rightNode, newElement);
            }
        }
    }

    @Override
    public boolean delete(Comparable<E> object) {
        if (rootNode != null && object != null && delete(null, rootNode, object)) {
            size--;
            return true;
        }
        else {
            return false;
        }
    }

    protected boolean delete(TreeNode<Comparable<E>> parentNode, TreeNode<Comparable<E>> currentNode, Comparable<E> object) {
        if (currentNode == null) {
            return false;
        }

        // Compare the object being deleted with current node's element
        int compareResult = object.compareTo((E) currentNode.element);

        // Go to left subtree if smaller
        if (compareResult < 0) {
            return delete(currentNode, currentNode.leftNode, object);

        } // Go to right subtree if larger
        else if (compareResult > 0) {
            return delete(currentNode, currentNode.rightNode, object);

        } // Delete current node
        else {
            boolean hasLeft = currentNode.leftNode != null;
            boolean hasRight = currentNode.rightNode != null;
            boolean noParent = parentNode == null;

            // Move right to parent
            if (!hasLeft && hasRight) {

                // Link to root directly
                if (noParent) {
                    parentNode = currentNode.rightNode;

                } // Link right node to parent
                else if (currentNode.equals(parentNode.leftNode)) {
                    parentNode.leftNode = currentNode.rightNode;
                }
                else {
                    parentNode.rightNode = currentNode.rightNode;
                }

            } // Move left to parent
            else if (!hasRight && hasLeft) {

                // Link to root directly
                if (noParent) {
                    rootNode = currentNode.leftNode;

                } // Link right node to parent
                else if (currentNode.equals(parentNode.leftNode)) {
                    parentNode.leftNode = currentNode.leftNode;
                }
                else {
                    parentNode.rightNode = currentNode.leftNode;
                }

            } // Delete current node
            else if (!hasLeft && !hasRight) {

                // Delete empty root node
                if (parentNode == null) {
                    rootNode = null;
                }
                else {
                    // Remove directly from parent
                    if (currentNode.equals(parentNode.leftNode)) {
                        parentNode.leftNode = null;
                    }
                    else {
                        parentNode.rightNode = null;
                    }
                }
            }
            else {
                // Make right to parent
                currentNode.element = currentNode.rightNode.element;

                // Now delete the right node instead because we already moved it to parent
                return delete(currentNode, currentNode.rightNode, currentNode.rightNode.element);
            }
        }

        return true;
    }

    public int getNumberOfLeaves() {
        return getNumberOfLeaves(rootNode);
    }

    protected int getNumberOfLeaves(TreeNode<Comparable<E>> currentNode) {
        if (currentNode == null) {
            return 0;
        }
        else if (currentNode.leftNode == null && currentNode.rightNode == null) {
            return 1;
        }
        else {
            return getNumberOfLeaves(currentNode.leftNode) + getNumberOfLeaves(currentNode.rightNode);
        }
    }

    @Override
    public void inOrder() {
        print(new InOrderIterator());
    }

    @Override
    public void preOrder() {
        print(new PreOrderIterator());
    }

    @Override
    public void postOrder() {
        print(new PostOrderIterator());
    }

    protected void print(Iterator it) {
        while (it.hasNext()) {
            System.out.print(it.next().toString() + " ");
        }
        System.out.println();
    }

    @Override
    public void clear() {
        rootNode = null;
        size = 0;
    }

    @Override
    public Iterator iterator() {
        return new InOrderIterator();
    }

    protected class TreeNode<E> {

        protected E element;
        protected TreeNode<E> leftNode;
        protected TreeNode<E> rightNode;

        public TreeNode(E element) {
            this.element = element;
        }

    }

    protected abstract class TreeIterator implements Iterator<E> {

        protected int current;
        protected final E[] iteratorArray;

        public TreeIterator() {
            current = -1;
            iteratorArray = (E[]) new Object[size];

            moveThroughNode(rootNode, 0);
        }

        // To be implemented based on iterator type
        protected abstract int moveThroughNode(TreeNode<Comparable<E>> currentNode, int index);

        @Override
        public boolean hasNext() {
            return current < iteratorArray.length - 1;
        }

        @Override
        public E next() {
            if (current < 0) {
                current = 0;
            }
            else {
                current++;
            }
            return iteratorArray[current];
        }

    }

    protected class InOrderIterator extends TreeIterator {

        public InOrderIterator() {
            super();
        }

        @Override
        protected int moveThroughNode(TreeNode<Comparable<E>> currentNode, int index) {
            if (currentNode != null) {
                // Left
                index = moveThroughNode(currentNode.leftNode, index);

                // Self
                iteratorArray[index++] = (E) currentNode.element;

                // Right
                index = moveThroughNode(currentNode.rightNode, index);
            }
            return index;
        }

    }

    protected class PreOrderIterator extends TreeIterator {

        public PreOrderIterator() {
            super();
        }

        @Override
        protected int moveThroughNode(TreeNode<Comparable<E>> currentNode, int index) {
            if (currentNode != null) {
                // Self
                iteratorArray[index++] = (E) currentNode.element;

                // Left
                index = moveThroughNode(currentNode.leftNode, index);

                // Right
                index = moveThroughNode(currentNode.rightNode, index);
            }
            return index;
        }

    }

    protected class PostOrderIterator extends TreeIterator {

        public PostOrderIterator() {
            super();
        }

        @Override
        protected int moveThroughNode(TreeNode<Comparable<E>> currentNode, int index) {
            if (currentNode != null) {
                // Left
                index = moveThroughNode(currentNode.leftNode, index);

                // Right
                index = moveThroughNode(currentNode.rightNode, index);

                // Self
                iteratorArray[index++] = (E) currentNode.element;
            }
            return index;
        }

    }

}
