package com.javarush.task.task18.task1803;

import java.io.FileInputStream;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/* 
Самые частые байты
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

        int max = count.values().stream().max(Comparator.comparingInt(a -> a)).get();
        for (Map.Entry<Byte, Integer> entry : count.entrySet()) {
            if (entry.getValue() == max)
                System.out.print(entry.getKey() + " ");
        }
    }
}
