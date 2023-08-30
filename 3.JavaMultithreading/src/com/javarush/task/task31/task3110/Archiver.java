package com.javarush.task.task31.task3110;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Archiver {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        ZipFileManager zipFileManager = new ZipFileManager(Paths.get(sc.nextLine()));
        Path fileToZip = Paths.get(sc.nextLine());
        zipFileManager.createZip(fileToZip);
    }
}
