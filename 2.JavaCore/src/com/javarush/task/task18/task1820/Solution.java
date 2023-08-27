package com.javarush.task.task18.task1820;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/* 
Округление чисел
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        try (FileInputStream file1 = new FileInputStream(sc.nextLine());
            FileOutputStream file2 = new FileOutputStream(sc.nextLine())) {
            byte[] file1Content = new byte[file1.available()];
            file1.read(file1Content);

            Arrays.stream(new String(file1Content)
                    .split(" "))
                    .map(Double::parseDouble)
                    .map(Math::round).forEach(x -> {
                        try {
                            file2.write((x + " ").getBytes());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });

        }
    }
}
