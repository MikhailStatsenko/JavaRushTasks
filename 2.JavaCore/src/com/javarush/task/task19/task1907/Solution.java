package com.javarush.task.task19.task1907;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/* 
Считаем слово
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine()));

        int wordsCnt = 0;
        while (fileReader.ready()) {
            wordsCnt += (int) Arrays.stream(fileReader.readLine().split("\\W"))
                    .filter(x -> x.matches("world"))
                    .count();
        }
        System.out.println(wordsCnt);

        reader.close();
        fileReader.close();
    }
}
