package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* 
Разархивируем файл
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        String resultFileName = args[0];
        File result = new File(resultFileName);

        List<FileInputStream> fileInputStreams = new ArrayList<>();

        List<String> fileNames = new ArrayList<>(Arrays.asList(args).subList(1, args.length));
        Collections.sort(fileNames);

        for (String name : fileNames) {
            fileInputStreams.add(new FileInputStream(name));
        }

        try (ZipInputStream zip = new ZipInputStream(new SequenceInputStream(Collections.enumeration(fileInputStreams)));
             FileOutputStream outputStream = new FileOutputStream(result)) {

            while (zip.getNextEntry() != null) {
                final int bufferSize = 4096;
                byte[] buffer = new byte[bufferSize];

                int readBytes;
                while ((readBytes = zip.read(buffer, 0, bufferSize)) > -1) {
                    outputStream.write(buffer, 0, readBytes);
                }
                outputStream.flush();
            }
        }
    }
}
