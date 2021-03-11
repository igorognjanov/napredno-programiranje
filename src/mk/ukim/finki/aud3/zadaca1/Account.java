package mk.ukim.finki.aud3.zadaca1;

abstract public class Account {
    protected String name;
    protected int id;
    protected double amount;


    public Account(String name, int id, double amount) {
        this.name = name;
        this.id = id;
        this.amount = amount;
    }

    public void addAmount(double amount){
        this.amount += amount;
    }

    public void withdrawAmount(double amount){
        this.amount -= amount;
    }

    abstract public double getAmount();


}
