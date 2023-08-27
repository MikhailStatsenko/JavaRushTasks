package com.javarush.task.task18.task1821;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/* 
Встречаемость символов
*/

public class Solution {
    public static void main(String[] args) {
        SortedMap<Character, Integer> map = new TreeMap<>();

        try (FileReader inputStream = new FileReader(args[0])) {
            while (inputStream.ready()) {
                char ch = (char) inputStream.read();
                map.putIfAbsent(ch, 0);
                map.put(ch, map.get(ch) + 1);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
