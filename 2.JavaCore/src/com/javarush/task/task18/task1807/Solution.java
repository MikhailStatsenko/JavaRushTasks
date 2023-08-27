package com.javarush.task.task18.task1807;

import java.io.FileInputStream;
import java.util.Scanner;

/* 
Подсчет запятых
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        try (Scanner sc = new Scanner(System.in);
             FileInputStream inputStream = new FileInputStream(sc.nextLine())) {
            byte[] buffer = new byte[inputStream.available()];
            int cnt = inputStream.read(buffer);
            int result = 0;
            for (int i = 0; i < cnt; i++) {
                if (buffer[i] == ',')
                    result++;
            }
            System.out.println(result);
        }
    }
}
