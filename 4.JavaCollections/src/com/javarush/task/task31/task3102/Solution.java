package com.javarush.task.task31.task3102;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* 
Находим все файлы
*/

public class Solution {
    public static List<String> getFileTree(String root) {
        return getFiles(root);
    }

    public static List<String> getFiles(String root) {
        File fileFromRoot = new File(root);

        if (fileFromRoot.isFile())
            return Collections.singletonList(root);

        List<String> result = new ArrayList<>();
        for (File file : fileFromRoot.listFiles()) {
            result.addAll(getFiles(root + File.separator + file.getName()));
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
