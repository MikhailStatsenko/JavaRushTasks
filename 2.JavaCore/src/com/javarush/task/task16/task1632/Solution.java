package com.javarush.task.task16.task1632;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* 
Клубок
*/

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);

    static {
        threads.add(new Thread1());
        threads.add(new Thread2());
        threads.add(new Thread3());
        threads.add(new Thread4());
        threads.add(new Thread5());
    }

    public static void main(String[] args) throws InterruptedException {

    }

    public static class Thread1 extends Thread {
        @Override
        public void run() {
            while (true) {

            }
        }
    }
    public static class Thread2 extends Thread {
        @Override
        public void run() {
            try {
                sleep(1);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
            }
        }
    }
    public static class Thread3 extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println("Ура");
                    sleep(500);
                }
            } catch (InterruptedException e) {

            }
        }
    }
    public static class Thread4 extends Thread implements Message {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {

            }
        }

        @Override
        public void showWarning() {
          interrupt();
        }
    }
    public static class Thread5 extends Thread {
        @Override
        public void run() {
            String line;
            double result = 0;
            Scanner sc = new Scanner(System.in);
            while (!(line = sc.nextLine()).equals("N")) {
                result += Double.parseDouble(line);
            }
            System.out.println(result);
        }

    }


}