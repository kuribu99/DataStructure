/*
 */
package com.kongmy.ds.p6;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Kong My
 */
public class Question1 {

    public static void main(String[] args) {

        Queue<Character> queue = new LinkedList<>();
        Stack<Character> stack = new Stack<>();
        Scanner scanner = new Scanner(System.in);
        
        // Ask for line
        System.out.print("Enter a line: ");
        String line = scanner.nextLine().trim();

        // Put into queue
        for (char ch : line.toCharArray()) {
            queue.add(ch);
        }

        // Take out one by one and put into stack
        while(!queue.isEmpty()) {
            stack.push(queue.remove());
        }

        StringBuilder builder = new StringBuilder();

        // Take out from stack
        while(!stack.isEmpty()) {
            builder.append(stack.pop());
        }
        
        System.out.println("Result: " + builder.toString());
    }

}
