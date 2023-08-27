package com.javarush.task.task14.task1419;

import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.IllegalFormatWidthException;
import java.util.IllformedLocaleException;
import java.util.List;

/* 
Нашествие исключений
*/

public class Solution {
    public static List<Exception> exceptions = new ArrayList<Exception>();

    public static void main(String[] args) {
        initExceptions();

        for (Exception exception : exceptions) {
            System.out.println(exception);
        }
    }

    private static void initExceptions() {
        try {
            float i = 1 / 0;
        } catch (Exception e) {
            exceptions.add(e);
            exceptions.add(new InterruptedIOException());
            exceptions.add(new RuntimeException());
            exceptions.add(new ArrayStoreException());
            exceptions.add(new SecurityException());
            exceptions.add(new IllegalAccessException());
            exceptions.add(new IllegalArgumentException());
            exceptions.add(new IllformedLocaleException());
            exceptions.add(new IllegalFormatWidthException(0));
            exceptions.add(new IllegalThreadStateException());
        }


    }
}
