/*
 */
package com.kongmy.ds.p5;

/**
 *
 * @author Kong My
 */
public class Question3 {

    public static void main(String[] args) {
        String[] array = new String[]{"Z", "D", "E", "R", "C", "A"};

        MySortedLinkedList<String> list = new MySortedLinkedList<>(array);
        System.out.println(list);
        list.remove(3);
        System.out.println(list);
        list.remove("A");
        System.out.println(list);
        list.remove("Z");
        System.out.println(list);
    }

}
