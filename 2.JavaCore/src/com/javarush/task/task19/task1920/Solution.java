package com.javarush.task.task19.task1920;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/* 
Самый богатый
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        Map<String, Double> salary = new TreeMap<>();
        BufferedReader fileReader = new BufferedReader(new FileReader(args[0]));
        double max = Double.MIN_VALUE;
        while (fileReader.ready()) {
            String[] data = fileReader.readLine().split(" ");
            salary.putIfAbsent(data[0], 0d);

            double sum = salary.get(data[0]) + Double.parseDouble(data[1]);
            max = Math.max(max, sum);
            salary.put(data[0], sum);
        }
        for (Map.Entry<String, Double> entry : salary.entrySet()) {
            if (entry.getValue() == max)
                System.out.print(entry.getKey() + " ");
        }
        fileReader.close();
    }
}
