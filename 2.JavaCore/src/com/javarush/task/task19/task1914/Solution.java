package com.javarush.task.task19.task1914;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/* 
Решаем пример
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream console = System.out;

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(byteStream);

        System.setOut(stream);

        testString.printSomething();

        System.setOut(console);

        String str = byteStream.toString().replaceAll("[\\s=]", "");
        String sign = str.replaceAll("\\d", "");
        List<Integer> op = Arrays.stream(str.split("[+\\-*]")).map(Integer::parseInt).collect(Collectors.toList());

        switch (sign) {
            case "+":
                System.out.println(byteStream.toString() + (op.get(0)+op.get(1)));
                break;
            case "-":
                System.out.println(byteStream.toString() + (op.get(0)-op.get(1)));
                break;
            case "*":
                System.out.println(byteStream.toString() + (op.get(0)*op.get(1)));
        }

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

