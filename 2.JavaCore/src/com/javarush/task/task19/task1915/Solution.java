package com.javarush.task.task19.task1915;

import java.io.*;

/* 
Дублируем текст
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) throws IOException {
        PrintStream console = System.out;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(byteStream);

        System.setOut(stream);
        testString.printSomething();

        FileOutputStream fileOutputStream = new FileOutputStream(reader.readLine());
        fileOutputStream.write(byteStream.toByteArray());
        console.println(byteStream);

        System.setOut(console);
        fileOutputStream.close();

        reader.close();

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}

