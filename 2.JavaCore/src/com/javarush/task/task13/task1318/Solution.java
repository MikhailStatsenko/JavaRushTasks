package com.javarush.task.task13.task1318;

import java.io.*;
import java.util.Scanner;

/* 
Чтение файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        FileInputStream fis = new FileInputStream(sc.nextLine());
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        while (br.ready())
            System.out.println(br.readLine());

        sc.close();
        fis.close();
        br.close();
    }
}