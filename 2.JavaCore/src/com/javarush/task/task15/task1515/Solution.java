package com.javarush.task.task15.task1515;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Статики-2
*/

public class Solution {
    public static int A;
    public static int B;

    public static final int MIN = min(A, B);

    static {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            A = Integer.parseInt(reader.readLine());
            B = Integer.parseInt(reader.readLine());
            System.out.println(min(A, B));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
    }

    public static int min(int a, int b) {
        return a < b ? a : b;
    }
}
