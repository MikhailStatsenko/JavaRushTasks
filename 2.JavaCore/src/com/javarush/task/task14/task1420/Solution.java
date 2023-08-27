package com.javarush.task.task14.task1420;

import java.util.Scanner;

/* 
НОД
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int num1 = sc.nextInt(), num2 = sc.nextInt();

        int min = Math.min(num1, num2);
        for (int div = min; div > 0; div--) {
            if (num1 % div == 0 && num2 % div == 0) {
                System.out.println(div);
                break;
            }
        }
    }
}
