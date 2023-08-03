package com.javarush.task.pro.task05.task0512;

/* 
Создаем мультимассив
*/

public class Solution {

    public static int[][][] multiArray = new int[][][]{{{4, 8, 15}, {16}}, {{23, 42}, {}}, {{1}, {2}, {3}, {4, 5}}};

    public static void main(String[] args) {
        for (int[][] arr : multiArray) {
            for (int[] row : arr) {
                for (int elem : row) {
                    System.out.print(elem + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
