package com.javarush.task.pro.task15.task1506;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

/* 
Фейсконтроль
*/

public class Solution {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            List<String> words = Files.readAllLines(Path.of(sc.nextLine()));
            for (String word : words) {
                System.out.print(word.replaceAll("[.,\\s]", ""));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

