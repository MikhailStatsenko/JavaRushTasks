package com.javarush.task.task18.task1823;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/* 
Нити и байты
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String fileName;
        while (!(fileName = sc.nextLine()).equals("exit")) {
            new ReadThread(fileName).start();
        }
    }

    public static class ReadThread extends Thread {
        String fileName;
        public ReadThread(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public void run() {
            int[] ascii = new int[128];
            try (FileInputStream inputStream = new FileInputStream(fileName)) {
                byte[] buffer = new byte[inputStream.available()];
                inputStream.read(buffer);
                for (byte elem : buffer) {
                    ascii[elem]++;
                }

                int max = ascii[0];
                for (int elem : ascii) {
                    if (elem > max) max = elem;
                }

                for (int i = 0; i < 128; i++) {
                    if (ascii[i] == max) {
                        synchronized (resultMap) {
                            resultMap.put(fileName, i);
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
