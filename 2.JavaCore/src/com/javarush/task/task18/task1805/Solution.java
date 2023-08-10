package com.javarush.task.task18.task1805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

/* 
Сортировка байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        try (Scanner sc = new Scanner(System.in);
             FileInputStream inputStream = new FileInputStream(sc.nextLine())) {
            Set<Byte> set = new HashSet<>();
            while (inputStream.available() > 0) {
                set.add((byte) inputStream.read());
            }
            set.stream().sorted().forEach(x -> System.out.print(x + " "));
        }
    }
}
