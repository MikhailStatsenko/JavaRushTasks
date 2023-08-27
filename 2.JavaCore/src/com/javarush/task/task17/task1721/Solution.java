package com.javarush.task.task17.task1721;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String fileName1 = sc.nextLine();
        String fileName2 = sc.nextLine();

        BufferedReader reader = new BufferedReader(new FileReader(fileName1));
        while (reader.ready()) {
            allLines.add(reader.readLine());
        }
        reader.close();

        reader = new BufferedReader(new FileReader(fileName2));
        while (reader.ready()) {
            forRemoveLines.add(reader.readLine());
        }

        Solution solution = new Solution();
        solution.joinData();
    }

    public void joinData() throws CorruptedDataException {
        if (new HashSet<>(allLines).containsAll(forRemoveLines)) {
            allLines.removeAll(forRemoveLines);
        } else {
            allLines.clear();
            throw new CorruptedDataException();
        }
    }
}
