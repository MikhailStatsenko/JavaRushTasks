package com.javarush.task.pro.task13.task1309;

import java.util.HashMap;

/* 
Успеваемость студентов
*/

public class Solution {
    public static HashMap<String, Double> grades = new HashMap<>();

    public static void main(String[] args) {
        addStudents();
        System.out.println(grades);
    }

    public static void addStudents() {
        grades.put("vasya", 2.0);
        grades.put("kolya", 3.0);
        grades.put("petya", 3.2);
        grades.put("anton", 5.0);
        grades.put("misha", 4.9);
    }
}
