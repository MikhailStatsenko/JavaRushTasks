package com.javarush.task.pro.task09.task0908;

import java.util.Arrays;
import java.util.regex.Pattern;

/* 
Двоично-шестнадцатеричный конвертер
*/

public class Solution {
    public static void main(String[] args) {
        String binaryNumber = "100111010000";
        System.out.println("Двоичное число " + binaryNumber + " равно шестнадцатеричному числу " + toHex(binaryNumber));
        String hexNumber = "9d0";
        System.out.println("Шестнадцатеричное число " + hexNumber + " равно двоичному числу " + toBinary(hexNumber));
    }

    public static String toHex(String binaryNumber) {
        if (binaryNumber == null)
            return "";

        StringBuilder binNum = new StringBuilder(binaryNumber);
        binNum.reverse();
        while (binNum.length() % 4 != 0)
            binNum.append("0");

        binNum.reverse();
        StringBuilder hexNum = new StringBuilder();
        for (int i = 0; i < binNum.length(); i += 4) {
            if (binNum.substring(i, i+4).equals("0000"))
                hexNum.append("0");
            else if (binNum.substring(i, i+4).equals("0001"))
                hexNum.append("1");
            else if (binNum.substring(i, i+4).equals("0010"))
                hexNum.append("2");
            else if (binNum.substring(i, i+4).equals("0011"))
                hexNum.append("3");
            else if (binNum.substring(i, i+4).equals("0100"))
                hexNum.append("4");
            else if (binNum.substring(i, i+4).equals("0101"))
                hexNum.append("5");
            else if (binNum.substring(i, i+4).equals("0110"))
                hexNum.append("6");
            else if (binNum.substring(i, i+4).equals("0111"))
                hexNum.append("7");
            else if (binNum.substring(i, i+4).equals("1000"))
                hexNum.append("8");
            else if (binNum.substring(i, i+4).equals("1001"))
                hexNum.append("9");
            else if (binNum.substring(i, i+4).equals("1010"))
                hexNum.append("a");
            else if (binNum.substring(i, i+4).equals("1011"))
                hexNum.append("b");
            else if (binNum.substring(i, i+4).equals("1100"))
                hexNum.append("c");
            else if (binNum.substring(i, i+4).equals("1101"))
                hexNum.append("d");
            else if (binNum.substring(i, i+4).equals("1110"))
                hexNum.append("e");
            else if (binNum.substring(i, i+4).equals("1111"))
                hexNum.append("f");
            else
                return "";
        }
        return hexNum.toString();
    }

    public static String toBinary(String hexNumber) {
        if (hexNumber == null)
            return "";

        StringBuilder binNum = new StringBuilder();
        for (int i = 0; i < hexNumber.length(); i++) {
            if (hexNumber.substring(i, i+1).equals("0"))
                binNum.append("0000");
            else if (hexNumber.substring(i, i+1).equals("1"))
                binNum.append("0001");
            else if (hexNumber.substring(i, i+1).equals("2"))
                binNum.append("0010");
            else if (hexNumber.substring(i, i+1).equals("3"))
                binNum.append("0011");
            else if (hexNumber.substring(i, i+1).equals("4"))
                binNum.append("0100");
            else if (hexNumber.substring(i, i+1).equals("5"))
                binNum.append("0101");
            else if (hexNumber.substring(i, i+1).equals("6"))
                binNum.append("0110");
            else if (hexNumber.substring(i, i+1).equals("7"))
                binNum.append("0111");
            else if (hexNumber.substring(i, i+1).equals("8"))
                binNum.append("1000");
            else if (hexNumber.substring(i, i+1).equals("9"))
                binNum.append("1001");
            else if (hexNumber.substring(i, i+1).equals("a"))
                binNum.append("1010");
            else if (hexNumber.substring(i, i+1).equals("b"))
                binNum.append("1011");
            else if (hexNumber.substring(i, i+1).equals("c"))
                binNum.append("1100");
            else if (hexNumber.substring(i, i+1).equals("d"))
                binNum.append("1101");
            else if (hexNumber.substring(i, i+1).equals("e"))
                binNum.append("1110");
            else if (hexNumber.substring(i, i+1).equals("f"))
                binNum.append("1111");
            else
                return "";
        }
        return binNum.toString();
    }
}
