/*
 */
package com.kongmy.ds.p7;

import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author Kong My
 */
public class Question1 {

    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();

        tree.insert(50);
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            tree.insert(rand.nextInt(100));
        }

        tree.inOrder();
        tree.preOrder();
        tree.postOrder();
        System.out.println("Number of leaves: " + tree.getNumberOfLeaves());
        System.out.println("Sum of all elements: " + sumTree(tree));

    }

    private static int sumTree(BinaryTree<Integer> tree) {
        int sum = 0;
        for (Iterator<Integer> it = tree.iterator(); it.hasNext();) {
            sum += it.next();
        }
        return sum;
    }

}
