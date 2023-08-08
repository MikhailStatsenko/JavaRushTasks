package com.javarush.task.task15.task1525;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* 
Файл в статическом блоке
*/

public class Solution {
    public static List<String> lines = new ArrayList<String>();

    static {
        String path = Statics.FILE_NAME;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            while (reader.ready()) {
                lines.add(reader.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(lines);
    }
}
