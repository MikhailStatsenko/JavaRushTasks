package com.javarush.task.task19.task1910;

import java.io.*;

/* 
Пунктуация
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine()));
        BufferedWriter writer = new BufferedWriter(new FileWriter(reader.readLine()));

        while (fileReader.ready()) {
            writer.write(fileReader.readLine().replaceAll("\\p{Punct}", ""));
        }

        reader.close();
        fileReader.close();
        writer.close();
    }
}
