package com.javarush.task.task16.task1617;

/* 
Отсчет на гонках
*/

public class Solution {
    public static volatile int numSeconds = 3;

    public static void main(String[] args) throws InterruptedException {
        RacingClock clock = new RacingClock();
        Thread.sleep(3500);
        clock.interrupt();
    }

    public static class RacingClock extends Thread {
        public RacingClock() {
            start();
        }

        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted() && numSeconds > 0){
                    Thread.sleep(1000);
                    System.out.print(numSeconds + " ");
                    numSeconds--;
                }
                System.out.println("Марш!");
            } catch (InterruptedException e) {
                while (numSeconds > 0){
                    System.out.print(numSeconds + " ");
                    numSeconds--;
                }
                System.out.println("Прервано!");
            }
        }
    }
}
