package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) {
        try {
            int start = string.indexOf(" ") + 1;
            int end = string.indexOf(" ", string.indexOf(" ",
                    string.indexOf(" ", string.indexOf(" ")  + 1) + 1) + 1);
            if (end != -1 && string.indexOf(" ", end + 1) == -1) {
                return string.substring(start);
            }
            return string.substring(start, string.indexOf(" ", end + 1));
        } catch (Exception e) {
            throw new TooShortStringException();
        }
    }

    public static class TooShortStringException extends RuntimeException {

    }
}
