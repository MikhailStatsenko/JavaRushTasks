package com.javarush.task.task32.task3201;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Запись в существующий файл
*/

public class Solution {
    public static void main(String... args) throws IOException {
        try(RandomAccessFile raf = new RandomAccessFile(args[0], "rw")) {
            long pos = Integer.parseInt(args[1]);
            pos = Math.min(pos, raf.length());

            raf.seek(pos);
            raf.write(args[2].getBytes());
        }
    }
}
