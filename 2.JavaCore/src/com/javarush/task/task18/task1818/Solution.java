package com.javarush.task.task18.task1818;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/* 
Два в одном
*/

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        try (FileOutputStream file1 = new FileOutputStream(sc.nextLine(), true);
            FileInputStream file2 = new FileInputStream(sc.nextLine());
            FileInputStream file3 = new FileInputStream(sc.nextLine())) {
            byte[] file2Content = new byte[file2.available()];
            byte[] file3Content = new byte[file3.available()];

            file2.read(file2Content);
            file3.read(file3Content);

            file1.write(file2Content);
            file1.write(file3Content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
