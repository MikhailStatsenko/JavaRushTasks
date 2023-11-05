package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;

/* 
Генератор паролей
*/

public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        ByteArrayOutputStream password = new ByteArrayOutputStream();
        for (int i = 0; i < 8; i++) {
            int rand = (int) (Math.random() * 3);
            if (rand == 0) {
                password.write((int) (Math.random() * 26  + 65));
            } else if (rand == 1) {
                password.write((int) (Math.random() * 26  + 97));
            } else {
                password.write((int) (Math.random() * 10  + 48));
            }
        }

        if (!check(password))
            password = getPassword();

        return password;
    }

    private static boolean check(ByteArrayOutputStream password) {
        boolean capitalLetter, lowercaseLetter, number;
        capitalLetter = lowercaseLetter = number = false;
        for (byte b : password.toByteArray()) {
            if (b >= 65 && b <= 90) {
                capitalLetter = true;
            } else if (b >= 97 && b <= 122) {
                lowercaseLetter = true;
            } else {
                number = true;
            }
        }
        return capitalLetter && lowercaseLetter && number;
    }
}
