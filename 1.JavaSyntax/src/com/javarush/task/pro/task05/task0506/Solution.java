package com.javarush.task.pro.task05.task0506;

import java.util.Scanner;

/* 
Минимальное из N чисел
*/

public class Solution {
    public static int[] array;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        array = new int[N];

        for (int i = 0; i < N; i++) {
            array[i] = sc.nextInt();
        }

        int min = array[0];
        for (int i = 0; i < N; i++) {
            if (array[i] < min)
                min = array[i];
        }
        System.out.println(min);
    }
}
