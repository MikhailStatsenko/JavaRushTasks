package com.javarush.task.task31.task3113;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;

/* 
Что внутри папки?
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Path path = Paths.get(sc.nextLine());

        if (!Files.isDirectory(path)) {
            System.out.println(path + " - не папка");
            return;
        }

        Visitor visitor = new Visitor();
        Files.walkFileTree(path, visitor);

        System.out.println("Всего папок - " + (visitor.dirCnt - 1));
        System.out.println("Всего файлов - " + visitor.fileCnt);
        System.out.println("Общий размер - " + visitor.totalSize);
    }

    public static class Visitor extends SimpleFileVisitor<Path> {
        public int dirCnt = 0;
        public int fileCnt = 0;
        public int totalSize = 0;
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            totalSize += Files.readAllBytes(file).length;
            fileCnt++;
            return super.visitFile(file, attrs);
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            dirCnt++;
            return super.preVisitDirectory(dir, attrs);
        }
    }
}
