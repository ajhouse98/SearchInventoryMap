package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
public class Maps {
    // the key is the product id, the value is a product object
    static HashMap<Integer, Product>inventory =new HashMap<Integer, Product>();
    public static void main(String[] args) throws FileNotFoundException {

        try {
            // this method loads product objects into inventory
            loadInventory();
            Scanner scanner = new Scanner(System.in);
            System.out.print("What item # are you interested in? ");
            int id = scanner.nextInt();
            Product matchedProduct = inventory.get(id);
            if (matchedProduct == null) {
                System.out.println("We don't carry that product");
                return;
            }
            System.out.printf("We carry %s and the price is $%.2f",
                    matchedProduct.getName(), matchedProduct.getPrice());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadInventory() throws IOException {
        try {
            FileReader fileReader = new FileReader("src/main/resources/inventory.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
           // bufferedReader.readLine();

            String input = "";

            while ((input = bufferedReader.readLine()) != null) {
                String[] data = input.split("\\|");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                double price = Double.parseDouble(data[2]);
                Product product = new Product(id, name, price);
                inventory.put(id, product);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}