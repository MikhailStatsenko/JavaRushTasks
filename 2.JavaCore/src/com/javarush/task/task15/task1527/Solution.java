package com.javarush.task.task15.task1527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/* 
Парсер реквестов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String url = reader.readLine();
        String[] params = url.substring(url.indexOf('?')+1).split("&");
        Arrays.stream(params).forEach(x -> System.out.println(x.split("=")[0]));
        for (String param : params) {
            String[] p = param.split("=");
            if (p[0].equals("obj")) {
                try {
                    alert(Double.parseDouble(p[1]));
                } catch (RuntimeException e) {
                    alert(p[1]);
                }
            }
        }
    }

    public static void alert(double value) {
        System.out.println("double: " + value);
    }

    public static void alert(String value) {
        System.out.println("String: " + value);
    }
}
