package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.Map;

/* 
Формируем WHERE
*/

public class Solution {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", null);
        map.put("country", null);
        map.put("city", null);
        map.put("age", null);
        System.out.println(getQuery(map));
    }

    public static String getQuery(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getValue() == null) continue;
            sb.append(entry.getKey()).append(" = ").append('\'').append(entry.getValue()).append('\'').append(" and ");
        }
        if (sb.length() > 0) {
            sb.delete(sb.lastIndexOf(" and"), sb.length());
            return sb.toString();
        }
        return "";
    }
}
