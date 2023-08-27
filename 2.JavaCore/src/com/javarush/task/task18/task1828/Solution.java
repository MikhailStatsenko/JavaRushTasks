package com.javarush.task.task18.task1828;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* 
Прайсы 2
*/

public class Solution {
    private static class Product {
        int id;
        public String productName;
        public String  price;
        public String quantity;

        public Product(int id, String productName, String price, String quantity) {
            this.id = id;
            this.productName = productName;
            this.price = price;
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return String.format("%-8d%-30s%-8s%-4s", id, productName, price, quantity);
        }

        public static Product parse(String str) {
            int id = Integer.parseInt(str.substring(0, 8).trim());
            String productName = str.substring(8, 38);
            String price = str.substring(38, 46);
            String quantity = str.substring(46, 50);
            return new Product(id, productName, price, quantity);
        }
    }
    public static void main(String[] args) throws IOException {
        if (args == null || args.length < 2) return;

        Scanner sc = new Scanner(System.in);
        String fileName = sc.nextLine();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        List<Product> products = new ArrayList<>();

        while (reader.ready()) {
            products.add(Product.parse(reader.readLine()));
        }
        sc.close();
        reader.close();

        StringBuilder result = new StringBuilder();
        if (args[0].equals("-u")) {
            int idToUpdate = Integer.parseInt(args[1]);
            for (Product product : products) {
                if (product.id == idToUpdate) {
                    product.productName = args[2];
                    product.price = args[3];
                    product.quantity = args[4];
                }
                result.append(product).append("\n");
            }
        } else if (args[0].equals("-d")) {
            int idToDelete = Integer.parseInt(args[1]);
            for (Product product : products) {
                if (product.id == idToDelete) continue;
                result.append(product).append("\n");
            }
        }

        FileWriter writer = new FileWriter(fileName);
        writer.write(result.toString());
        writer.close();
    }

    private int getId(String str) {
        return Integer.parseInt(str.substring(0, 8).trim());
    }
}
