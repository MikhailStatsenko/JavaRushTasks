package com.javarush.task.task18.task1825;

import java.io.*;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/* 
Собираем файл
*/

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String fileName, resultFileName = "";
        int maxPartNumber = 1;
        while (!(fileName = sc.nextLine()).equals("end")) {
            resultFileName = fileName.substring(0, fileName.lastIndexOf('.'));
            int partNumber = Integer.parseInt(fileName.replace(resultFileName + ".part", ""));
            maxPartNumber = Math.max(maxPartNumber, partNumber);
        }

        try(FileOutputStream resultFile = new FileOutputStream(resultFileName, true)) {
            for (int i = 1; i <= maxPartNumber; i++) {
                try(FileInputStream currPart = new FileInputStream(resultFileName+".part"+i);) {
                    byte[] buffer = new byte[currPart.available()];
                    currPart.read(buffer);
                    resultFile.write(buffer);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
