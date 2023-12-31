package com.javarush.task.pro.task09.task0907;

/*
Шестнадцатеричный конвертер
*/

public class Solution {
    private static final String HEX = "0123456789abcdef";

    public static void main(String[] args) {
        int decimalNumber = 1256;
        System.out.println("Десятичное число " + decimalNumber + " равно шестнадцатеричному числу " + toHex(decimalNumber));
        String hexNumber = "4e8";
        System.out.println("Шестнадцатеричное число " + hexNumber + " равно десятичному числу " + toDecimal(hexNumber));
    }

    public static String toHex(int decimalNumber) {
        StringBuilder hex = new StringBuilder();
        while (decimalNumber > 0) {
            hex.append(HEX.substring(decimalNumber%16, decimalNumber%16 + 1));
            decimalNumber /= 16;
        }
        hex.reverse();
        return hex.toString();
    }

    public static int toDecimal(String hexNumber) {
        if (hexNumber == null)
            return 0;

        int hexLen = hexNumber.length();

        int decimal = 0;
        for (int i = 0; i < hexLen; i++) {
            decimal += HEX.indexOf(hexNumber.charAt(hexLen - i - 1)) * Math.pow(16, i);
        }

        return decimal;
    }
}
