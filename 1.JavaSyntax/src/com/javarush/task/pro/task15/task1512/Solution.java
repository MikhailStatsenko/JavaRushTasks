package com.javarush.task.pro.task15.task1512;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.Scanner;

/* 
Задом наперед
*/

public class Solution {
    public static ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    public static PrintStream stream = new PrintStream(outputStream);

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        printSomething(scanner.nextLine());
        String reversed = new StringBuilder(outputStream.toString()).reverse().toString();
        outputStream.reset();
        printSomething(reversed);
        System.out.println(outputStream);
    }

    public static void printSomething(String str) {
        stream.print(str);
    }
}