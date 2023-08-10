package com.javarush.task.task18.task1804;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/* 
Самые редкие байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        Map<Byte, Integer> count = new HashMap<>();
        try (Scanner sc = new Scanner(System.in);
             FileInputStream inputStream = new FileInputStream(sc.nextLine())) {
            while (inputStream.available() > 0) {
                byte aByte = (byte) inputStream.read();
                count.putIfAbsent(aByte, 0);
                count.put(aByte, count.get(aByte) + 1);
            }
        }

        int min = count.values().stream().min(Comparator.comparingInt(a -> a)).get();
        for (Map.Entry<Byte, Integer> entry : count.entrySet()) {
            if (entry.getValue() == min)
                System.out.print(entry.getKey() + " ");
        }
    }
}
