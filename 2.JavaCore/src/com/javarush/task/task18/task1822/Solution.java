package com.javarush.task.task18.task1822;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/* 
Поиск данных внутри файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        try (BufferedReader reader = new BufferedReader(new FileReader(sc.nextLine()))) {
            while (reader.ready()) {
                String str = reader.readLine();
                if (str.split(" ")[0].equals(args[0])) {
                    System.out.println(str);
                    break;
                }
            }
        }
        sc.close();
    }
}
