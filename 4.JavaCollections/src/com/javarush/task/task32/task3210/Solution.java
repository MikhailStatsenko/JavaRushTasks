package com.javarush.task.task32.task3210;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) throws IOException {
        try(RandomAccessFile raf = new RandomAccessFile(args[0], "rw")) {
            long pos = Integer.parseInt(args[1]);
            String text = args[2];

            raf.seek(pos);
            byte[] bytes = new byte[text.length()];
            raf.read(bytes, 0, text.length());

            raf.seek(raf.length());
            if (text.equals(new String(bytes))) {
                raf.write("true".getBytes());
            } else {
                raf.write("false".getBytes());
            }
        }
    }
}
