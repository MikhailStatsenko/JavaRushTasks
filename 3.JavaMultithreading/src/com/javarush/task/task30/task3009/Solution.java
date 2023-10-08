package com.javarush.task.task30.task3009;

import java.util.HashSet;
import java.util.Set;

/* 
Палиндром?
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getRadix("112"));        //expected output: [3, 27, 13, 15]
        System.out.println(getRadix("123"));        //expected output: [6]
        System.out.println(getRadix("5321"));       //expected output: []
        System.out.println(getRadix("1A"));         //expected output: []
    }

    private static Set<Integer> getRadix(String number) {
        Set<Integer> result = new HashSet<>();
        for (int base = 2; base <= 36; base++) {
            try {
                if (isPalindrome(Long.toString(Long.parseLong(number), base)))
                    result.add(base);
            } catch (NumberFormatException ignored) {}
        }
        return result;
    }

    private static boolean isPalindrome(String number) {
        int len = number.length();
        for (int i = 0; i < len / 2; i++) {
            if (number.charAt(i) != number.charAt(len-i-1))
                return false;
        }
        return true;
    }
}