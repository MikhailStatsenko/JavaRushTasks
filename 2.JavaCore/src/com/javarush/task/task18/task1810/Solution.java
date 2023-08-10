package com.javarush.task.task18.task1810;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/* 
DownloadException
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        try (Scanner sc = new Scanner(System.in)) {
            while (sc.hasNextLine()) {
                if (new FileInputStream(sc.nextLine()).available() < 1000)
                    throw new DownloadException();
            }
        }
    }

    public static class DownloadException extends Exception {
    }
}
