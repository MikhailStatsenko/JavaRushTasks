package com.javarush.task.task18.task1824;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/* 
Файлы и исключения
*/

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String name = sc.nextLine();
            try (FileInputStream inputStream = new FileInputStream(name)){

            } catch (FileNotFoundException e) {
                System.out.println(name);
                return;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
