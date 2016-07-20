/*
 */
package com.kongmy.ds.p5;

/**
 *
 * @author Kong My
 */
public class Question1 {

    public static void main(String[] args) {
        String[] list1 = new String[]{"Tom", "George", "Peter", "Jean", "Jane"};
        String[] list2 = new String[]{"Tom", "George", "Michael", "Michelle", "Daniel"};

        MyArrayList<String> arrayList1 = new MyArrayList<>(list1);
        MyArrayList<String> arrayList2 = new MyArrayList<>(list2);

        System.out.println(arrayList1);
        System.out.println(arrayList2);

        System.out.println("Performing addAll");
        arrayList1.addAll(arrayList2);
        System.out.println(arrayList1);

        arrayList1 = new MyArrayList<>(list1);
        arrayList2 = new MyArrayList<>(list2);
        System.out.println("Performing removeAll");
        arrayList1.removeAll(arrayList2);
        System.out.println(arrayList1);

        arrayList1 = new MyArrayList<>(list1);
        arrayList2 = new MyArrayList<>(list2);
        System.out.println("Performing retainAll");
        arrayList1.retainAll(arrayList2);
        System.out.println(arrayList1);
    }

}
