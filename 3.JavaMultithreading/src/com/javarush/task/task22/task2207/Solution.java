package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 
Обращенные слова
*/

public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader = new BufferedReader(new FileReader(console.readLine()));
        List<String> words = new ArrayList<>();
        while (reader.ready()) {
            words.addAll(Arrays.asList(reader.readLine().split(" ")));
        }

        result = new ArrayList<>();
        Set<Integer> added = new HashSet<>();
        for (int i = 0; i < words.size() - 1; i++) {
            String reversed = new StringBuilder(words.get(i)).reverse().toString();
            for (int j = i + 1; j < words.size(); j++) {
                if (words.get(j).equals(reversed) && !added.contains(i) && !added.contains(j)) {
                    Pair newPair = new Pair();
                    newPair.first = words.get(i);
                    newPair.second = words.get(j);
                    result.add(newPair);
                    added.add(i);
                    added.add(j);
                }
            }
        }
        console.close();
        reader.close();
        result.forEach(System.out::println);
    }

    public static class Pair {
        String first;
        String second;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return first == null && second == null ? "" :
                    first == null ? second :
                            second == null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
