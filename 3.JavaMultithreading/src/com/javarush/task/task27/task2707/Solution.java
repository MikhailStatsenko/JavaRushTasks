package com.javarush.task.task27.task2707;

/* 
Определяем порядок захвата монитора
*/

public class Solution {
    public void someMethodWithSynchronizedBlocks(Object obj1, Object obj2) {
        synchronized (obj1) {
            synchronized (obj2) {
                System.out.println(obj1 + " " + obj2);
            }
        }
    }

    public static boolean isLockOrderNormal(final Solution solution, final Object o1, final Object o2) throws Exception {
        Thread tryToBlockThread = new Thread(()-> {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                    synchronized (o2) {
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread invokeMethodThread = new Thread(()-> solution.someMethodWithSynchronizedBlocks(o1, o2));
        tryToBlockThread.start();
        invokeMethodThread.start();
        Thread.sleep(2000);
        return invokeMethodThread.getState() != Thread.State.BLOCKED;
    }

    public static void main(String[] args) throws Exception {
        final Solution solution = new Solution();
        final Object o1 = new Object();
        final Object o2 = new Object();
        System.out.println(isLockOrderNormal(solution, o1, o2));
    }
}
