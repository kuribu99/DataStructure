/*
 */
package com.kongmy.ds.p4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;

/**
 *
 * @author Kong My
 */
public class Question2 {

    public static void main(String[] args) {
        String fileName = "input.txt";
        String line;
        TreeSet<String> list = new TreeSet<>();

        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                line = scanner.nextLine().trim();
                if (Character.isLetter(line.charAt(0))) {
                    list.add(line);
                }
            }
            scanner.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println("File missing in action");
        }

        for (String str : list) {
            System.out.println(str);
        }
    }

}
