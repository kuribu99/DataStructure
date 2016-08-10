/*
 */
package com.kongmy.ds.p6;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author Kong My
 */
public class Question2 {

    private static final String OPERATORS = "+-*/";

    public static void main(String[] args) throws Exception {
        Queue<String> queue = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);

        // Ask for expression
        String line = "-    +    *    9    +    2    8    *    +    4    8    6    3";
        //System.out.print("Enter a prefix expression: ");
        //String line = scanner.nextLine().trim();

        // Use regex to split and add to queue
        for (String str : line.split("[^0-9\\+\\-\\*/]+")) {
            if (str.trim().length() > 0) {
                queue.add(str.trim());
            }
        }

        if (queue.size() == 0) {
            System.out.println("Empty input, please check you input");
        }
        else {
            System.out.println("Result: " + evaluate(queue));
        }
    }

    private static String evaluate(Queue<String> queue) throws Exception {
        String tempString1;
        String tempString2;
        String tempString3;
        Expression tempExpr;

        // Evaluate until queue is left with 1 item
        while (queue.size() > 1) {
            tempString1 = queue.remove();

            if (isOperator(tempString1)) {
                tempString2 = queue.peek();

                // If 2nd string is operand, continue to check on 3rd
                if (!isOperator(tempString2)) {

                    // Remove the 2nd string so that we can peek on 3rd
                    queue.remove();
                    tempString3 = queue.peek();

                    // If 3rd is also operand, evaluate
                    if (!isOperator(tempString3)) {
                        queue.remove();

                        // Create expression
                        tempExpr = Expression.make(tempString1, tempString2, tempString3);

                        // Evaluate and put back
                        tempString1 = String.valueOf(tempExpr.evaluate());

                        // Push back to queue
                        queue.add(tempString1);

                    }
                    else {
                        // Push back both to queue
                        queue.add(tempString1);
                        queue.add(tempString2);
                    }
                }
                else {
                    // Push back to queue
                    queue.add(tempString1);
                }

            }
            else {
                // Push back to queue
                queue.add(tempString1);
            }
        }
        return queue.remove();
    }

    private static boolean isOperator(String str) {
        return OPERATORS.contains(str);
    }

    private static class Expression {

        private final char operator;
        private final int operandLeft;
        private final int operandRight;

        public static Expression make(String operator, String operandLeft, String operandRight) {
            int left;
            int right;

            if (operator.length() != 1 || !isOperator(operator)) {
                throw new IllegalArgumentException("Invalid operator");
            }

            try {
                left = Integer.parseInt(operandLeft);
                right = Integer.parseInt(operandRight);

            }
            catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid operand");
            }

            return new Expression(operator.charAt(0), left, right);
        }

        private Expression(char operator, int operandLeft, int operandRight) {
            this.operator = operator;
            this.operandLeft = operandLeft;
            this.operandRight = operandRight;
        }

        public int evaluate() throws Exception {
            switch (operator) {
                case '+':
                    return operandLeft + operandRight;
                case '-':
                    return operandLeft - operandRight;
                case '*':
                    return operandLeft * operandRight;
                case '/':
                    return operandLeft / operandRight;
            }
            return Integer.MAX_VALUE;
        }

    }

}
