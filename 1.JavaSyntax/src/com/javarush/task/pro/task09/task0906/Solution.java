package com.javarush.task.pro.task09.task0906;

import java.util.regex.Pattern;

/* 
Двоичный конвертер
*/

public class Solution {
    public static void main(String[] args) {
        int decimalNumber = Integer.MAX_VALUE;
        System.out.println("Десятичное число " + decimalNumber + " равно двоичному числу " + toBinary(decimalNumber));
        String binaryNumber = "1111111111111111111111111111111";
        System.out.println("Двоичное число " + binaryNumber + " равно десятичному числу " + toDecimal(binaryNumber));
    }

    public static String toBinary(int decimalNumber) {
        StringBuilder binary = new StringBuilder();
        while (decimalNumber > 0) {
            binary.append(decimalNumber%2);
            decimalNumber /= 2;
        }
        binary.reverse();
        return binary.toString();
    }

    public static int toDecimal(String binaryNumber) {
        if (binaryNumber == null)
            return 0;

        int binLen = binaryNumber.length();

        int decimal = 0;
        for (int i = 0; i < binLen; i++) {
            decimal += Integer.parseInt(binaryNumber.substring(binLen - i - 1, binLen - i)) * Math.pow(2, i);
        }

        return decimal;
    }
}
