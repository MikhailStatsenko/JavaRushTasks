package com.javarush.task.task18.task1826;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* 
Шифровка
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        if (args == null || args.length < 3) return;

        byte key = 123;

        FileInputStream fileName = new FileInputStream(args[1]);
        FileOutputStream fileOutputStream = new FileOutputStream(args[2]);

        if (args[0].equals("-e") || args[0].equals("-d")) {
            byte[] bytesToEncrypt = new byte[fileName.available()];
            fileName.read(bytesToEncrypt);
            fileOutputStream.write(encrypt(bytesToEncrypt, key));
        }

        fileName.close();
        fileOutputStream.close();
    }

    private static byte[] encrypt(byte[] data, byte key) {
        int length = data.length;

        byte[] result = new byte[length];
        for (int i = 0; i< length; i++) {
            result[i] = (byte) (data[i] ^ key);
        }

        return result;
    }

}
