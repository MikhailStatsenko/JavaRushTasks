package com.javarush.task.task13.task1326;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/* 
Сортировка четных чисел из файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        ArrayList<Integer> list = new ArrayList<>();
        FileInputStream file = new FileInputStream(sc.nextLine());
        sc.close();

        sc = new Scanner(file);
        while (sc.hasNextInt()) {
            int num = sc.nextInt();
            if (num % 2 == 0)
                list.add(num);
        }
        list.stream().sorted().forEach(System.out::println);
        sc.close();
    }
}
