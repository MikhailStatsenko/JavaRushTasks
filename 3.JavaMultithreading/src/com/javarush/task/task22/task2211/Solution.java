package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/* 
Смена кодировки
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream(args[0]);
        OutputStream outputStream = new FileOutputStream(args[1]);

        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);
        outputStream.write(
                new String(buffer, Charset.forName("Windows-1251"))
                        .getBytes(Charset.forName("UTF-8")));

        inputStream.close();
        outputStream.close();
    }
}
