package labs.l01.zadaca02;

import java.util.Objects;
import java.util.Random;

public class Account {
    private final String name;
    private long ID;
    private String  balance;







    public Account(String ime, String balance) {
        this.name = ime;
        this.balance = balance;
        Random r = new Random ();
        ID = r.nextLong ();
        if(ID < 0) ID = ID * -1;
        System.out.println (ID);
    }
    public String getBalance(){
        return balance;
    }
    public String getName(){
        return name;
    }
    public long getID(){
        return ID;
    }
    public void setBalance(String balance){
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        Account account = (Account) o;
        return ID == account.ID &&
                Objects.equals (name, account.name) &&
                Objects.equals (balance, account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash (name, ID, balance);
    }

    @Override
    public String toString() {
        return "Name:" + name + '\n' +  "Balance: " + balance + '\n';
    }

    public static void main(String[] args) {
        Account acc = new Account ("Igor", "17556");
        FlatAmountProvisionTransaction f =
                new FlatAmountProvisionTransaction (1232143, 1231232, "20.00$", "12.00$");
    }
}
