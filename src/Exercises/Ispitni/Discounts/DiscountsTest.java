package Exercises.Ispitni.Discounts;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Discounts
 */

public class DiscountsTest {
//    public static void main(String[] args) {
//        Discounts discounts = new Discounts();
//        int stores = discounts.readStores(System.in);
//        System.out.println("Stores read: " + stores);
//        System.out.println("=== By average discount ===");
//        discounts.byAverageDiscount().forEach(System.out::println);
//        System.out.println("=== By total discount ===");
//        discounts.byTotalDiscount().forEach(System.out::println);
//    }
}

// Vashiot kod ovde


class Store{
    private String name;

}

class Discounts{


    public void readStores(InputStream inputStream){
        Scanner scanner = new Scanner (inputStream);
        while (scanner.hasNextLine ()){
            String line = scanner.nextLine ();
            String [] parts = line.split (" ");
            String name = parts[0];
            for(int i=0; i< parts.length; i+=2){

            }
        }

    }
}