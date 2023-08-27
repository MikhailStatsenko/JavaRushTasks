package com.javarush.task.task18.task1812;

import java.io.IOException;
import java.util.Scanner;

/* 
Расширяем AmigoOutputStream
*/

public class QuestionFileOutputStream implements AmigoOutputStream {
    AmigoOutputStream fileOutputStream;

    public QuestionFileOutputStream(AmigoOutputStream fileOutputStream) {
        this.fileOutputStream = fileOutputStream;
    }

    @Override
    public void flush() throws IOException {
        fileOutputStream.flush();
    }

    @Override
    public void write(int b) throws IOException {
        fileOutputStream.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        fileOutputStream.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        fileOutputStream.write(b, off, len);
    }

    @Override
    public void close() throws IOException {
        System.out.print("Вы действительно хотите закрыть поток? Д/Н");
        Scanner sc = new Scanner(System.in);
        if (sc.nextLine().equals("Д"))
            fileOutputStream.close();

    }
}

