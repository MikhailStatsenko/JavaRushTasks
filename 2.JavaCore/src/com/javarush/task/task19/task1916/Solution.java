package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1Name = reader.readLine();
        String file2Name = reader.readLine();
        reader.close();

        BufferedReader reader1 = new BufferedReader(new FileReader(file1Name));
        BufferedReader reader2 = new BufferedReader(new FileReader(file2Name));

        List<String> file1Content = reader1.lines().collect(Collectors.toList());
        List<String> file2Content = reader2.lines().collect(Collectors.toList());

        reader1.close();
        reader2.close();

        int i = 0, j = 0;
        while (i < file1Content.size() && j < file2Content.size()) {
            if (file1Content.get(i).equals(file2Content.get(j))) {
                lines.add(new LineItem(Type.SAME, file1Content.get(i)));
                i++;
                j++;
            } else if (file1Content.get(i).equals(file2Content.get(j+1))) {
                lines.add(new LineItem(Type.ADDED, file2Content.get(j)));
                j++;
            } else if (file1Content.get(i+1).equals(file2Content.get(j))) {
                lines.add(new LineItem(Type.REMOVED, file1Content.get(i)));
                i++;
            }
        }
        if (i < file1Content.size())
            lines.add(new LineItem(Type.REMOVED, file1Content.get(i)));
        else if (j < file2Content.size())
            lines.add(new LineItem(Type.ADDED, file2Content.get(j)));
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }

        @Override
        public String toString() {
            return type.name() + " " + line;
        }
    }
}
