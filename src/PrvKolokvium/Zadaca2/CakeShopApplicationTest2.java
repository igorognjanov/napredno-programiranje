package PrvKolokvium.Zadaca2;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;


abstract class Item{
    protected String itemName;
    protected int itemPrice;

    public Item(String itemName, int itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    abstract public void increaseBy50();
}

class Pie extends Item{
    public Pie(String itemName, int itemPrice) {
        super (itemName, itemPrice);
    }

    @Override
    public void increaseBy50() {
        itemPrice+=50;
    }
}

class Cake extends Item{
    public Cake(String itemName, int itemPrice) {
        super (itemName, itemPrice);
    }

    @Override
    public void increaseBy50() {
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

    public int getSumOfAllItems(){
        int sum=0;
        for(int i=0; i<items.size (); i++){
            sum += items.get (i).itemPrice;
        }
        return sum;
    }

    public int getNumberOfPies(){
        int count = 0;
        for(int i=0; i<items.size (); i++){
            if(items.get (i) instanceof Pie)
                count++;
        }
        return count;
    }

    public int getNumberOfCakes(){
        return getNumberOfItems () - getNumberOfPies ();
    }

    public void addItem(String itemName, int itemPrice, char c){
        if(c == 'C')
            items.add (new Cake (itemName, itemPrice));
        else items.add (new Pie (itemName, itemPrice));
    }

    public int getNumberOfItems(){
        return items.size ();
    }

    public void increasePriceOfPies(){
        for(int i=0; i<items.size (); i++){
            items.get (i).increaseBy50 ();
        }
    }
}



class CakeShopApplication{
    private int minOrderItems;
    private ArrayList<Order> orders;

    public CakeShopApplication(int minOrderItems){
        orders = new ArrayList<> ();
        this.minOrderItems = minOrderItems;
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
                order.addItem (parts[i], Integer.parseInt (parts[i+1]), parts[i].charAt (0));
                count++;
            }

            try {
                addOrder (order);
            } catch (InvalidOrderException e) {
                System.out.println (e.getMessage ());
            }
        }
        return count;
    }

    public void increasePriceOfPiesOfAllOrders(){
        for(int i=0; i<orders.size (); i++){
            orders.get (i).increasePriceOfPies ();
        }
    }

    public void addOrder(Order order) throws InvalidOrderException {
        if(order.getNumberOfItems () < minOrderItems) throw new InvalidOrderException (order.getOrderId ());
        orders.add (order);
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

    public void printAllOrders(OutputStream outputStream){
        PrintWriter pw = new PrintWriter (outputStream);
        increasePriceOfPiesOfAllOrders ();
        List<Order> temp = orders.stream ()
                .sorted (Comparator.comparingInt (Order::getSumOfAllItems))
                .collect(Collectors.toList ());
        Collections.reverse (temp);
        temp.forEach (order -> System.out.println (order.getOrderId () + " " + order.getNumberOfItems ()
                + " " + order.getNumberOfPies () + " " + order.getNumberOfCakes () + " " + order.getSumOfAllItems ()));
    }
}

class InvalidOrderException extends Exception{
    private String id;

    public InvalidOrderException(String id){
        this.id = id;
    }

    public String getMessage(){
        return "The order with id " + id + " has less items than the minimum allowed.";
    }
}

public class CakeShopApplicationTest2 {

    public static void main(String[] args) {
        CakeShopApplication cakeShopApplication = new CakeShopApplication(4);

        System.out.println("--- READING FROM INPUT STREAM ---");
        cakeShopApplication.readCakeOrders(System.in);

        System.out.println("--- PRINTING TO OUTPUT STREAM ---");
        cakeShopApplication.printAllOrders(System.out);
    }
}