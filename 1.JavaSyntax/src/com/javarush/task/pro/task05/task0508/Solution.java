package com.javarush.task.pro.task05.task0508;

import java.util.Scanner;

/* 
Удаляем одинаковые строки
*/

public class Solution {
    public static String[] strings;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 6; i++) {
            strings[i] = sc.nextLine();
        }

        for (int i = 0; i < 5; i++) {
            String curr = strings[i];
            for (int j = i+1; j < 6; j++) {
                if (strings[j] != null && strings[j] == curr)
                    strings[j] = null;
            }
        }

        for (int i = 0; i < strings.length; i++) {
            System.out.print(strings[i] + ", ");
        }
    }
}
