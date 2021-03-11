package labs.l03;
import java.util.Scanner;

public class PizzaOrderTest {

    static interface Item{
        boolean equals(Item item);

        int getPrice();
    }

    public static class PizzaItem implements Item{
        private String pizza;
        private int price;

        public PizzaItem(String pizza) throws InvalidPizzaTypeException {
            if(pizza.equals ("Standart")) {
                this.pizza = pizza;
                this.price = 10;
            }
            else if(pizza.equals ("Pepperoni")){
                this.pizza = pizza;
                this.price = 12;
            }
            else if(pizza.equals ("Vegetarian")){
                this.pizza = pizza;
                this.price = 8;
            }
            else throw new InvalidPizzaTypeException ("InvalidPizzaTypeException");
        }
        @Override
        public boolean equals(Item item){
            if(this == item) return true;
            if(this.getClass () != item.getClass ()) return false;
            PizzaItem that = (PizzaItem) item;
            if(this.pizza.equals ( that.pizza)) return true;
            return false;
        }

        @Override
        public String toString(){
            return pizza;
        }

        public int getPrice(){ return price; }

    }
    public static class ExtraItem implements Item{
        private String extra;
        private int price;

        public ExtraItem(String extra) throws InvalidExtraTypeException {
            if(extra.equals ("Coke")) {
                this.extra = extra;
                this.price = 5;
            }
            else if(extra.equals ("Ketchup")){
                this.extra = extra;
                this.price = 3;
            }
            else throw new InvalidExtraTypeException("InvalidExtraTypeException");
        }

        @Override
        public String toString(){
            return extra;
        }

        @Override
        public boolean equals(Item item){
            if(this == item) return true;
            if(this.getClass () != item.getClass ()) return false;
            ExtraItem that = (ExtraItem) item;
            if(this.extra.equals (that.extra)) return true;
            return false;
        }
        public int getPrice(){ return price; }

    }

    public static class InvalidPizzaTypeException extends Exception{
        private String message;
        public InvalidPizzaTypeException(String s){
            this.message = s;
        }
        public String getMessage(){ return message;}
    }

    public static class InvalidExtraTypeException extends Exception{
        private String message;
        public InvalidExtraTypeException(String s){
            this.message = s;
        }
        public String getMessage(){ return message;}
    }

    public static class Order{
        private Item[] items;
        private int[] times;
        private boolean isLocked;

        public Order(){
            items = new Item[0];
            times = new int[0];
            this.isLocked =false;
        }

        public void addItem(Item item, int count) throws ItemOutOfStockException, OrderLockedException {
            if(isLocked) throw new OrderLockedException();
            if(count > 10) throw new ItemOutOfStockException(item);
            for(int i=0; i<items.length; i++) {
                if (items[i].equals (item)) {
                    times[i] = count;
                }
            }
            int n = items.length;
            Item[] temp = new Item[n+1];
            int[] temp2 = new int[n+1];
            for(int i=0; i<n; i++) {
                temp[i] = items[i];
                temp2[i] = times[i];
            }
            temp[n] = item;
            temp2[n] = count;
            this.items = temp;
            this.times = temp2;
        }

        public int getPrice(){
            int sum = 0;
            for(int i=0; i<items.length; i++)
                sum += items[i].getPrice () * times[i];
            return sum;
        }

        public void displayOrder(){
            for(int i=0; i<items.length; i++){
                System.out.printf ("%3d.%-22sx %d%5d$", i+1, items[i].toString (),
                        times[i], times[i] * items[i].getPrice());
            }
        }

        public void removeItem(int index) throws OrderLockedException{
            if(isLocked) throw new OrderLockedException ();
            if(index >= items.length || index < 0) throw new ArrayIndexOutOfBoundsException (index);
            for(int i=index; i<items.length-1; i++){
                times[i] = times[i+1];
            }
        }

        public void lock(){
            isLocked = true;
        }
    }


    public static class OrderLockedException extends Exception{
        public OrderLockedException(){
            System.out.println ("OrderLockedException");
        }
    }

    public static class ItemOutOfStockException extends Exception{
        private Item item;

        public ItemOutOfStockException(Item item){
            this.item = item;
        }

    }



    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if (k == 0) { //test Item
            try {
                String type = jin.next();
                String name = jin.next();
                Item item = null;
                if (type.equals("Pizza")) item = new PizzaItem(name);
                else item = new ExtraItem(name);
                System.out.println(item.getPrice());
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
        }
        if (k == 1) { // test simple order
            Order order = new Order();
            while (true) {
                try {
                    String type = jin.next();
                    String name = jin.next();
                    Item item = null;
                    if (type.equals("Pizza")) item = new PizzaItem(name);
                    else item = new ExtraItem(name);
                    if (!jin.hasNextInt()) break;
                    order.addItem(item, jin.nextInt());
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
            }
            jin.next();
            System.out.println(order.getPrice());
            order.displayOrder();
            while (true) {
                try {
                    String type = jin.next();
                    String name = jin.next();
                    Item item = null;
                    if (type.equals("Pizza")) item = new PizzaItem(name);
                    else item = new ExtraItem(name);
                    if (!jin.hasNextInt()) break;
                    order.addItem(item, jin.nextInt());
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
            }
            System.out.println(order.getPrice());
            order.displayOrder();
        }
        if (k == 2) { // test order with removing
            Order order = new Order();
            while (true) {
                try {
                    String type = jin.next();
                    String name = jin.next();
                    Item item = null;
                    if (type.equals("Pizza")) item = new PizzaItem(name);
                    else item = new ExtraItem(name);
                    if (!jin.hasNextInt()) break;
                    order.addItem(item, jin.nextInt());
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
            }
            jin.next();
            System.out.println(order.getPrice());
            order.displayOrder();
            while (jin.hasNextInt()) {
                try {
                    int idx = jin.nextInt();
                    order.removeItem(idx);
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
            }
            System.out.println(order.getPrice());
            order.displayOrder();
        }
        if (k == 3) { //test locking & exceptions
            Order order = new Order();
            try {
                order.lock();
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
            try {
                order.addItem(new ExtraItem("Coke"), 1);
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
            try {
                order.lock();
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
            try {
                order.removeItem(0);
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
        }
    }

}