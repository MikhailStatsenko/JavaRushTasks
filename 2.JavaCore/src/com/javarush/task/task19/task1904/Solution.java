package com.javarush.task.task19.task1904;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/* 
И еще один адаптер
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        PersonScannerAdapter var = new PersonScannerAdapter(new Scanner(System.in));
        var.read();
    }

    public static class PersonScannerAdapter implements PersonScanner{
        private Scanner fileScanner;

        public PersonScannerAdapter(Scanner fileScanner) {
            this.fileScanner = fileScanner;
        }

        @Override
        public Person read() throws IOException {
            String[] data = fileScanner.nextLine().split(" ");
            DateFormat format = new SimpleDateFormat("ddMMyyyy");
            try {
                return new Person(data[1], data[2], data[0], format.parse(data[3] + data[4] + data[5]));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void close() throws IOException {
            fileScanner.close();
        }
    }
}
