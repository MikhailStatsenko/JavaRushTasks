package com.javarush.task.task15.task1529;

import java.util.Scanner;

/* 
Осваивание статического блока
*/

public class Solution {
    public static void main(String[] args) {

    }

    static {
        reset();
    }

    public static CanFly result;

    public static void reset() {
        Scanner sc = new Scanner(System.in);
        String type = sc.nextLine();
        if (type.equals("helicopter"))
            result = new Helicopter();
        else if (type.equals("plane")) {
            result = new Plane(sc.nextInt());
        }
        sc.close();
    }
}
