package com.javarush.task.task19.task1906;

import java.io.*;

/* 
Четные символы
*/

public class Solution {
    public static void main(String[] args) {
        String file1Name;
        String file2Name;
        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
            file1Name = console.readLine();
            file2Name = console.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (FileReader fileReader = new FileReader(file1Name);
             FileWriter fileWriter = new FileWriter(file2Name)) {
            StringBuilder dataForFile2 = new StringBuilder();
            int index = 1;
            while (fileReader.ready()) {
                char ch = (char) fileReader.read();
                if (index % 2 == 0)
                    dataForFile2.append(ch);
                index++;
            }

            fileWriter.write(dataForFile2.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
