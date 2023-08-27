package com.javarush.task.task19.task1908;

import java.io.*;

/* 
Выделяем числа
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String file1Name = console.readLine();
        String file2Name = console.readLine();
        console.close();

        try (BufferedReader file1 = new BufferedReader(new FileReader(file1Name));
             BufferedWriter file2 = new BufferedWriter(new FileWriter(file2Name))) {

            StringBuilder dataForFile2 = new StringBuilder();
            while (file1.ready()) {
                for (String str : file1.readLine().split(" ")) {
                    if (str.matches("\\d*"))
                        dataForFile2.append(str).append(" ");
                }
            }
            file2.write(dataForFile2.toString());
        }

    }
}
