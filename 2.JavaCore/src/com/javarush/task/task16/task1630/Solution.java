package com.javarush.task.task16.task1630;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/* 
Последовательный вывод файлов
*/

public class Solution {
    public static String firstFileName;
    public static String secondFileName;

    static {
        Scanner sc = new Scanner(System.in);
        firstFileName = sc.nextLine();
        secondFileName = sc.nextLine();
        sc.close();
    }

    public static void main(String[] args) throws InterruptedException {
        systemOutPrintln(firstFileName);
        systemOutPrintln(secondFileName);
    }

    public static void systemOutPrintln(String fileName) throws InterruptedException {
        ReadFileInterface f = new ReadFileThread();
        f.setFileName(fileName);
        f.start();
        f.join();
        System.out.println(f.getFileContent());
    }

    public interface ReadFileInterface {

        void setFileName(String fullFileName);

        String getFileContent();

        void join() throws InterruptedException;

        void start();
    }

    public static class ReadFileThread extends Thread implements ReadFileInterface {
        String fullFileName;
        String content;
        @Override
        public void setFileName(String fullFileName) {
            this.fullFileName = fullFileName;
        }

        @Override
        public String getFileContent() {
            if (content == null)
                return "";
            return content;
        }

        @Override
        public void run() {
            Scanner sc = null;
            try {
                sc = new Scanner(new FileReader(fullFileName));
                StringBuilder sb = new StringBuilder();
                while (sc.hasNextLine()) {
                    sb.append(sc.nextLine()).append(" ");
                }
                content = sb.toString();
            } catch (IOException e) {

            } finally {
                if (sc != null)
                    sc.close();
            }
        }
    }
}
