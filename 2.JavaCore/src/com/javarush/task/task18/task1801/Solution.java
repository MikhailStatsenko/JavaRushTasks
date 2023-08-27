package com.javarush.task.task18.task1801;

import java.io.FileInputStream;
import java.util.Scanner;

/* 
Максимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        FileInputStream inputStream = new FileInputStream(sc.nextLine());
        int max = Byte.MIN_VALUE;
        while (inputStream.available() > 0) {
            int aByte = inputStream.read();
            if (aByte > max)
                max = aByte;
        }
        System.out.println(max);
        inputStream.close();
    }
}
