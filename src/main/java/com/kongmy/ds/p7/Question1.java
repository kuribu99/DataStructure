/*
 */
package com.kongmy.ds.p7;

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
    }

}
