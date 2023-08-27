package com.javarush.task.task13.task1319;

import java.io.*;

/* 
Писатель в файл с консоли
*/

public class Solution {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new FileWriter(reader.readLine()))) {

            while (true) {
                String line = reader.readLine();
                bw.write(line + "\n");
                if (line.equals("exit")) break;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
