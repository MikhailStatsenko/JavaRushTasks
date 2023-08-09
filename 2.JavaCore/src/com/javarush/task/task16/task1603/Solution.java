package com.javarush.task.task16.task1603;

import java.util.ArrayList;
import java.util.List;

/* 
Список и нити
*/

public class Solution {
    public static volatile List<Thread> list = new ArrayList<>(5);

    public static void main(String[] args) {
        SpecialThread task1 = new SpecialThread();
        list.add(new Thread(task1));
        SpecialThread task2 = new SpecialThread();
        list.add(new Thread(task2));
        SpecialThread task3 = new SpecialThread();
        list.add(new Thread(task3));
        SpecialThread task4 = new SpecialThread();
        list.add(new Thread(task4));
        SpecialThread task5 = new SpecialThread();
        list.add(new Thread(task5));

    }

    public static class SpecialThread implements Runnable {
        public void run() {
            System.out.println("it's a run method inside SpecialThread");
        }
    }
}
