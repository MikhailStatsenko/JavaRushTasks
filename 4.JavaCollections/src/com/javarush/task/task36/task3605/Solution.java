package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Pattern;

/* 
Использование TreeSet
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        TreeSet<Character> set = new TreeSet<>();

        try (Scanner scanner = new Scanner(Paths.get(args[0]))) {
            while (scanner.hasNextLine()) {
                char[] chars = scanner.nextLine().toLowerCase().toCharArray();
                for (char ch : chars) {
                    if (Pattern.matches("[a-z]",String.valueOf(ch))) {
                        set.add(ch);
                    }
                }
            }
        }

        int out = 0;
        Iterator<Character> it = set.iterator();
        while (out < 5 && out < set.size()) {
            System.out.print(it.next());
            out++;
        }
    }
}
