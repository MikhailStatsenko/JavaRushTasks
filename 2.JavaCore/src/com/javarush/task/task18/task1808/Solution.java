package com.javarush.task.task18.task1808;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

/* 
Разделение файла
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        try (Scanner sc = new Scanner(System.in);
             FileInputStream file1 = new FileInputStream(sc.nextLine());
             FileOutputStream file2 = new FileOutputStream(sc.nextLine());
             FileOutputStream file3 = new FileOutputStream(sc.nextLine())) {

            int file2Len = file1.available()/2 + file1.available()%2;
            int file3Len = file1.available()/2;

            byte[] file2Content = new byte[file2Len];
            byte[] file3Content = new byte[file3Len];

            file1.read(file2Content);
            file1.read(file3Content);

            file2.write(file2Content);
            file3.write(file3Content);
        }
    }
}
