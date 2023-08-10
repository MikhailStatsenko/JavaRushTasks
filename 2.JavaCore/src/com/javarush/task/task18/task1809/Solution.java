package com.javarush.task.task18.task1809;

import java.io.*;
import java.util.*;

/* 
Реверс файла
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        try (Scanner sc = new Scanner(System.in);
             FileInputStream file1 = new FileInputStream(sc.nextLine());
             FileOutputStream file2 = new FileOutputStream(sc.nextLine())) {

            byte[] file1Content = new byte[file1.available()];
            file1.read(file1Content);
            byte[] file1ContentReversed = new byte[file1Content.length];

            for (int i = file1Content.length - 1, j = 0; i >= 0; i--, j++)
                file1ContentReversed[j] = file1Content[i];

            file2.write(file1ContentReversed);
        }
    }
}
