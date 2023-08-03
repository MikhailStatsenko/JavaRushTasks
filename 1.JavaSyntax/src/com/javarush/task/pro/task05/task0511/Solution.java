package com.javarush.task.pro.task05.task0511;

import java.util.Scanner;

/* 
Создаем двумерный массив
*/

public class Solution {
    public static int[][] multiArray;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        multiArray = new int[num][];
        for (int i = 0; i < num; i++) {
            int curLen = sc.nextInt();
            multiArray[i] = new int[curLen];
        }
    }
}
