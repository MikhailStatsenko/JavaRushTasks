package com.javarush.task.task18.task1817;

import java.io.*;

/* 
Пробелы
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        int totalCount = 0, spaceCount = 0;
        try (FileInputStream inputStream = new FileInputStream(args[0])) {
            while (inputStream.available() > 0) {
                char chr = (char) inputStream.read();
                if (chr == ' ')
                    spaceCount++;
                totalCount++;
            }
        }

        System.out.println(String.format("%.2f", 1.0*spaceCount/totalCount * 100));
    }
}
