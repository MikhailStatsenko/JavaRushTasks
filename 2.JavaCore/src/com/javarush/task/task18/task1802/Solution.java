package com.javarush.task.task18.task1802;

import java.io.FileInputStream;
import java.util.Scanner;

/* 
Минимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        int min = Byte.MAX_VALUE;
        try (Scanner sc = new Scanner(System.in);
             FileInputStream inputStream = new FileInputStream(sc.nextLine())) {
            while (inputStream.available() > 0)
                min = Math.min(min, inputStream.read());
        }
        System.out.println(min);
    }
}
