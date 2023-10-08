package com.javarush.task.task30.task3010;

import java.math.BigInteger;
import java.util.regex.Pattern;

/* 
Минимальное допустимое основание системы счисления
*/

public class Solution {
    public static void main(String[] args) {
        String result = "incorrect";
        for (int base = 2; base <= 36; base++) {
            try {
                new BigInteger(args[0], base);
                result = Integer.toString(base);
                break;
            } catch (Exception ignored) {}
        }
        System.out.println(result);
    }
}