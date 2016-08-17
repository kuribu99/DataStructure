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

        for (int i = 0; i < 5; i++) {
            int randNumber = rand.nextInt(100);
            System.out.printf("Has %d: %s\n", randNumber, String.valueOf(tree.search(randNumber)));
        }

        for (int i = 0; i < 8; i++) {
            int randNumber = rand.nextInt(100);
            while (!tree.search(randNumber)) {
                randNumber = rand.nextInt(100);
            }
            System.out.println("Removed " + randNumber);
            tree.delete(randNumber);
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
