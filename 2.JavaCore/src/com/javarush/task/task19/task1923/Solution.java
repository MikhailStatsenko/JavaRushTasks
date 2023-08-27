package com.javarush.task.task19.task1923;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/* 
Слова с цифрами
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        FileWriter writer = new FileWriter(args[1]);

        StringBuilder resultString = new StringBuilder();
        while (reader.ready()) {
            String[] result = Arrays.stream(reader.readLine().split(" "))
                    .filter(x -> x.matches(".*\\d+.*")).toArray(String[]::new);
            for (String st : result)
                resultString.append(st).append(" ");
        }
        writer.write(resultString.toString());

        reader.close();
        writer.close();
    }
}
