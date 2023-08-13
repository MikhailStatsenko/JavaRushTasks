package com.javarush.task.task19.task1919;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/* 
Считаем зарплаты
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        Map<String, Double> salary = new TreeMap<>();
        BufferedReader fileReader = new BufferedReader(new FileReader(args[0]));
        while (fileReader.ready()) {
            String[] data = fileReader.readLine().split(" ");
            salary.putIfAbsent(data[0], 0d);
            salary.put(data[0], salary.get(data[0]) + Double.parseDouble(data[1]));
        }
        for (Map.Entry<String, Double> entry : salary.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        fileReader.close();
    }
}
