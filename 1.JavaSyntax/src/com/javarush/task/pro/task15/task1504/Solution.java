package com.javarush.task.pro.task15.task1504;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/* 
Перепутанные байты
*/

public class Solution {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in);
             InputStream input = Files.newInputStream(Path.of(sc.nextLine()));
             OutputStream output = Files.newOutputStream(Path.of(sc.nextLine()))) {
            while (input.available() > 0) {
                if (input.available() == 1) {
                    output.write(input.read());
                    break;
                }
                byte[] bytes = input.readNBytes(2);
                byte tmp = bytes[0];
                bytes[0] = bytes[1];
                bytes[1] = tmp;
                output.write(bytes);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

