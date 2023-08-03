package com.javarush.task.pro.task08.task0809;

/* 
Counter

*/
public class Solution {

    public static void main(String[] args) throws InterruptedException {
        printNumbers();
    }

    public static void printNumbers() throws InterruptedException {
        int i = 1;
        for (; i < 10; i++) {
            System.out.println(i);
            Thread.sleep(1, 300000);
        }
        System.out.println(i);
    }
}
