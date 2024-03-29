package com.javarush.task.task35.task3507;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/

public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> result = new HashSet<>();
        if (!pathToAnimals.endsWith("\\") && !pathToAnimals.endsWith("/")) {
            pathToAnimals = pathToAnimals + "/";
        }
        File dir = new File(pathToAnimals);
        String[] paths = dir.list((dir1, name) -> name.toLowerCase().endsWith(".class"));
        for (String p : paths) {
            try {
                final String finalPathToAnimals = pathToAnimals;
                boolean hasInterface = false;
                boolean hasConstructor = false;

                Class<?> clazz = getClass(p, finalPathToAnimals);

                Class<?>[] interfaces = clazz.getInterfaces();
                for (Class<?> i : interfaces) {
                    if (Animal.class == i) {
                        hasInterface = true;
                        break;
                    }
                }
                if (!hasInterface) continue;

                Constructor[] constructors = clazz.getConstructors();
                for (Constructor c : constructors) {
                    if (c.getParameterTypes().length == 0) {
                        hasConstructor = true;
                        break;
                    }
                }
                if (!hasConstructor) continue;
                result.add((Animal) clazz.newInstance());
            } catch (Exception ignored) { }
        }
        return result;
    }

    private static Class<?> getClass(String p, String finalPathToAnimals) throws ClassNotFoundException {
        ClassLoader loader = new ClassLoader() {
            @Override
            public Class<?> findClass(String className) throws ClassNotFoundException {
                try {
                    byte[] b = fetchClassFromFS(finalPathToAnimals + className + ".class");
                    return defineClass(null, b, 0, b.length);
                } catch (IOException ex) {
                    return super.findClass(className);
                }
            }
        };
        String className = p.substring(0, p.length() - 6);
        return loader.loadClass(className);
    }


    private static byte[] fetchClassFromFS(String path) throws IOException {
        InputStream is = new FileInputStream(path);

        long length = new File(path).length();
        byte[] bytes = new byte[(int) length];

        int numRead;
        int offset = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + path);
        }
        is.close();
        return bytes;
    }
}
