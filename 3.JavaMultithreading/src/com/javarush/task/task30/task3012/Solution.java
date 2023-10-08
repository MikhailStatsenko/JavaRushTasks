package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Base64;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(74);
    }

    public void createExpression(int number) {
        StringBuilder sb = new StringBuilder(number +  " = ");
        int pow = 0;
        int remainder;
        do {
            remainder = number % 3;
            number /= 3;
            if (remainder == 1)
                sb.append(" + ").append((int) Math.pow(3, pow));
            else if (remainder == 2) {
                sb.append(" - ").append((int) Math.pow(3, pow));
                number++;
            }
            pow++;
        } while (number > 0);
        System.out.println(sb);
    }
}