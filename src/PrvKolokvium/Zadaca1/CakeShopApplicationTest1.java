package PrvKolokvium.Zadaca1;


import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

class Item{
    private String itemName;
    private int itemPrice;

    public Item(String itemName, int itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }
}

class Order{
    private String orderId;
    private ArrayList<Item> items;

    public Order(String name){
        orderId = name;
        items = new ArrayList<> ();
    }

    public String getOrderId() {
        return orderId;
    }

    public void addItem(String itemName, int itemPrice){
        items.add (new Item (itemName, itemPrice));
    }

    public int getNumberOfItems(){
        return items.size ();
    }
}



class CakeShopApplication{
    private ArrayList<Order> orders;

    public CakeShopApplication(){
        orders = new ArrayList<> ();
    }

    public int readCakeOrders(InputStream inputStream){
        Scanner scanner = new Scanner (inputStream);
        int count = 0;
        while(scanner.hasNextLine ()){
            String line = scanner.nextLine ();
            String[] parts = line.split ("\\s+");
            String orderName = parts[0];
            Order order = new Order (orderName);
            for(int i=1; i<parts.length; i+=2){
                order.addItem (parts[i], Integer.parseInt (parts[i+1]));
                count++;
            }
            orders.add (order);
        }
        return count;
    }

    public Order getOrderWithMostItems(){
        Order ret = orders.get (0);
        int maxItems = orders.get (0).getNumberOfItems ();
        for(int i=1; i<orders.size (); i++){
            if(orders.get (i).getNumberOfItems () > maxItems){
                ret = orders.get (i);
            }
        }
        return ret;
    }

    public void printLongestOrder(OutputStream outputStream){
        PrintWriter pw = new PrintWriter (outputStream);
        pw.println (getOrderWithMostItems ().getOrderId () + " " + getOrderWithMostItems ().getNumberOfItems ());
        pw.flush ();
    }
}



public class CakeShopApplicationTest1 {

    public static void main(String[] args) {
        CakeShopApplication cakeShopApplication = new CakeShopApplication();

        System.out.println("--- READING FROM INPUT STREAM ---");
        System.out.println(cakeShopApplication.readCakeOrders(System.in));

        System.out.println("--- PRINTING LARGEST ORDER TO OUTPUT STREAM ---");
        cakeShopApplication.printLongestOrder(System.out);
    }
}