package com.javarush.task.task26.task2601;

import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.function.ToIntFunction;

/* 
Почитать в инете про медиану выборки
*/

public class Solution {

    public static void main(String[] args) {

    }

    public static Integer[] sort(Integer[] array) {
        int length = array.length;

        List<Integer> lst = new ArrayList<>(Arrays.asList(array));
        Collections.sort(lst);

        int median = length % 2 != 0 ? lst.get(length/2) : (lst.get(length/2) + lst.get(length/2 - 1))/2;

        lst.sort((o1, o2) -> {
            int diff1 = Math.abs(o1 - median);
            int diff2 = Math.abs(o2 - median);
            if (diff1 == diff2) {
                return Integer.compare(o1, o2);
            } else
                return diff1 - diff2;
        });


        return lst.stream().toArray(Integer[]::new);
    }
}
