package com.javarush.task.task25.task2512;

import java.util.Stack;

/* 
Живем своим умом
*/

public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();

        Stack<Throwable> exceptionStack = new Stack<>();
        while (e != null) {
            exceptionStack.push(e);
            e = e.getCause();
        }

        while (!exceptionStack.isEmpty()) {
            System.out.println(exceptionStack.pop());
        }
    }

    public static void main(String[] args) throws Exception {
        Thread.currentThread().setUncaughtExceptionHandler(new Solution());
        throw new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI")));
    }
}
