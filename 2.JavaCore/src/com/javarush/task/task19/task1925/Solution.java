package com.javarush.task.task19.task1925;

import java.io.*;

/* 
Длинные слова
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));

        StringBuilder resultString = new StringBuilder();
        while (reader.ready()) {
            String[] words = reader.readLine().split(" ");
            for (String word : words) {
                if (word.length() > 6)
                    resultString.append(word).append(",");
            }
        }
        resultString.deleteCharAt(resultString.length()-1);
        writer.write(resultString.toString());

        reader.close();
        writer.close();
    }
}
