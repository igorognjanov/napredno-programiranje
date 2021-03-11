package labs.l01.SistemZaBankarskoRabotenje_VtorObid;


import org.w3c.dom.ls.LSOutput;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

class Converter{
    public static double toDouble(String amount){
        amount = amount.substring (0, amount.length () -1);
        return Double.parseDouble (amount);
    }
    public static String toString(double amount){
        DecimalFormat decimalFormat = new DecimalFormat ("0.00");
        String ret = decimalFormat.format (amount) + "$";
        return ret;
    }
}


class Account{
    private String name;
    private long ID;
    private String balance;

    public Account(String name, String balance){
        this.name = name;
        this.balance = balance;
        Random r = new Random ();
        this.ID = Math.abs (r.nextLong ());
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return ID;
    }

    public String getBalance() {
        return balance;
    }
    @Override
    public String toString(){
        return String.format ("Name: %s\nBalance: %s\n", name, balance);
    }
}

abstract class Transaction{
    private long fromID;
    private long toId;
    private String description;
    private String amount;

    public Transaction(long fromId, long toId, String description, String amount){
        this.amount = amount;
        this.fromID = fromId;
        this.description = description;
        this.toId = toId;
    }

    public long getFromID() {
        return fromID;
    }

    public long getToId() {
        return toId;
    }

    public String getAmount() {
        return amount;
    }
    public String getDescription(){
        return description;
    }

    public abstract double getMoneyWithProvision();
    public abstract double getProvision();
}

class FlatAmountProvisionTransaction extends Transaction{
    private String flatProvision;

    public FlatAmountProvisionTransaction(long fromId, long toId,String amount, String flatProvision){
        super(fromId, toId, "FlatAmount", amount);
        this.flatProvision = flatProvision;
    }

    public String getFlatAmount(){
        return flatProvision;
    }

    public double getMoneyWithProvision(){
        double ret = Converter.toDouble (getAmount ()) + Converter.toDouble (flatProvision);
        return ret;
    }

    public double getProvision(){
        return Converter.toDouble (flatProvision);
    }
}

class FlatPercentProvisionTransaction extends Transaction{
    private int percent;

    public FlatPercentProvisionTransaction(long fromId, long toId, String amount, int percent) {
        super (fromId, toId, "FlatPercent", amount);
        this.percent = percent;
    }

    public int getPercent() {
        return percent;
    }

    public double getMoneyWithProvision(){
        double ret = Converter.toDouble (this.getAmount ()) +
                getProvision ();
        return ret;
    }

    public double getProvision(){
        return (((int) Converter.toDouble (getAmount ())) / 100.00) * percent;
    }
}

class Bank{
    private String name;
    private List<Account> accounts;
    private String totalTransfers;
    private String totalProvision;

    public Bank(String name, Account accounts[]){
        this.name = name;
        this.accounts = new ArrayList<> (accounts.length);
        this.accounts = Arrays.asList(accounts.clone());
        totalProvision = "0.00$";
        totalTransfers = "0.00$";
    }

    public String totalProvision(){ return totalProvision;}
    public String totalTransfers(){ return totalTransfers;}

    public boolean checkIfInBank(long ID){
        return accounts.stream ().anyMatch (account -> account.getId () == ID);
    }

    public Account getAccountIndex(long ID){
        for(int i=0; i<accounts.size (); i++){
            if(accounts.get (i).getId () == ID)
                return accounts.get (i);
        }
        return null;
    }

    public boolean checkIfHasEnoughMoney(Account account, Transaction transaction){
        if(Converter.toDouble (account.getBalance ()) >= transaction.getMoneyWithProvision ())
            return true;
        return false;
    }

    public boolean makeTransaction(Transaction t){
        if(checkIfInBank (t.getFromID ()) && checkIfInBank (t.getToId ())){
            Account sender = getAccountIndex (t.getFromID ());
            if(!checkIfHasEnoughMoney (sender, t))
                return false;
            Account receiver = getAccountIndex (t.getToId ());
            double newBalanceOfSender = Converter.toDouble (sender.getBalance ()) - t.getMoneyWithProvision ();
            double newBalanceOfReceiver = Converter.toDouble (receiver.getBalance ()) +
                    Converter.toDouble (t.getAmount ());
            sender.setBalance (Converter.toString (newBalanceOfSender));
            receiver.setBalance (Converter.toString (newBalanceOfReceiver));
            totalProvision = Converter.toString (Converter.toDouble (totalProvision) + t.getProvision ());
            totalTransfers = Converter.toString (Converter.toDouble (totalTransfers) +
                    Converter.toDouble (t.getAmount ()));
            return true;
        }
        else{
            return false;
        }
    }

    public Account[] getAccounts(){
        Account[] acc = new Account[accounts.size ()];
        return accounts.toArray (acc);
    }

    @Override
    public String toString(){
        String str = "Name: " + name;
        str += "\n\n";
        for(int i=0; i<accounts.size (); i++){
            str += accounts.get (i).toString ();
        }
        return str;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        Bank bank = (Bank) o;
        return name.equals (bank.name) &&
                accounts.equals (bank.accounts) &&
                totalTransfers.equals (bank.totalTransfers) &&
                totalProvision.equals (bank.totalProvision);
    }

    @Override
    public int hashCode() {
        return Objects.hash (name, accounts, totalTransfers, totalProvision);
    }
}

public class BankTester {
    /*
    public static void main(String[] args) {
        String am = "240.60$";
        //System.out.printf ("%s",Converter.toString (Converter.toDouble (am)));
        Account[] accounts = new Account[3];
        accounts[0] = new Account ("Igor", "100.21$");
        accounts[1] = new Account ("ognjanov", "100.00$");
        accounts[2] = new Account ("sasamatic", "21212.21$");
        Bank bank = new Bank ("igor banka", accounts);
        System.out.println (bank.checkIfInBank (1231233121));

    }
*/
    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        String test_type = jin.nextLine();
        switch (test_type) {
            case "typical_usage":
                testTypicalUsage(jin);
                break;
            case "equals":
                testEquals();
                break;
        }
        jin.close();
    }

    private static void testEquals() {
        Account a1 = new Account("Andrej", "20.00$");
        Account a2 = new Account("Andrej", "20.00$");
        Account a3 = new Account("Andrej", "30.00$");
        Account a4 = new Account("Gajduk", "20.00$");
        List<Account> all = Arrays.asList(a1, a2, a3, a4);
        if (!(a1.equals(a1)&&!a1.equals(a2)&&!a2.equals(a1) && !a3.equals(a1)
                && !a4.equals(a1)
                && !a1.equals(null))) {
            System.out.println("Your account equals method does not work properly.");
            return;
        }
        Set<Long> ids = all.stream().map(Account::getId).collect(Collectors.toSet());
        if (ids.size() != all.size()) {
            System.out.println("Different accounts have the same IDS. This is not allowed");
            return;
        }
        FlatAmountProvisionTransaction fa1 = new FlatAmountProvisionTransaction(10, 20, "20.00$", "10.00$");
        FlatAmountProvisionTransaction fa2 = new FlatAmountProvisionTransaction(20, 20, "20.00$", "10.00$");
        FlatAmountProvisionTransaction fa3 = new FlatAmountProvisionTransaction(20, 10, "20.00$", "10.00$");
        FlatAmountProvisionTransaction fa4 = new FlatAmountProvisionTransaction(10, 20, "50.00$", "50.00$");
        FlatAmountProvisionTransaction fa5 = new FlatAmountProvisionTransaction(30, 40, "20.00$", "10.00$");
        FlatPercentProvisionTransaction fp1 = new FlatPercentProvisionTransaction(10, 20, "20.00$", 10);
        FlatPercentProvisionTransaction fp2 = new FlatPercentProvisionTransaction(10, 20, "20.00$", 10);
        FlatPercentProvisionTransaction fp3 = new FlatPercentProvisionTransaction(10, 10, "20.00$", 10);
        FlatPercentProvisionTransaction fp4 = new FlatPercentProvisionTransaction(10, 20, "50.00$", 10);
        FlatPercentProvisionTransaction fp5 = new FlatPercentProvisionTransaction(10, 20, "20.00$", 30);
        FlatPercentProvisionTransaction fp6 = new FlatPercentProvisionTransaction(30, 40, "20.00$", 10);
        if (fa1.equals(fa1) &&
                !fa2.equals(null) &&
                fa2.equals(fa1) &&
                fa1.equals(fa2) &&
                fa1.equals(fa3) &&
                !fa1.equals(fa4) &&
                !fa1.equals(fa5) &&
                !fa1.equals(fp1) &&
                fp1.equals(fp1) &&
                !fp2.equals(null) &&
                fp2.equals(fp1) &&
                fp1.equals(fp2) &&
                fp1.equals(fp3) &&
                !fp1.equals(fp4) &&
                !fp1.equals(fp5) &&
                !fp1.equals(fp6)) {
            System.out.println("Your transactions equals methods do not work properly.");
            return;
        }
        Account accounts[] = new Account[]{a1, a2, a3, a4};
        Account accounts1[] = new Account[]{a2, a1, a3, a4};
        Account accounts2[] = new Account[]{a1, a2, a3};
        Account accounts3[] = new Account[]{a1, a2, a3, a4};

        Bank b1 = new Bank("Test", accounts);
        Bank b2 = new Bank("Test", accounts1);
        Bank b3 = new Bank("Test", accounts2);
        Bank b4 = new Bank("Sample", accounts);
        Bank b5 = new Bank("Test", accounts3);

        if (!(b1.equals(b1) &&
                !b1.equals(null) &&
                !b1.equals(b2) &&
                !b2.equals(b1) &&
                !b1.equals(b3) &&
                !b3.equals(b1) &&
                !b1.equals(b4) &&
                b1.equals(b5))) {
            System.out.println("Your bank equals method do not work properly.");
            return;
        }
        //accounts[2] = a1;
        if (!b1.equals(b5)) {
            System.out.println("Your bank equals method do not work properly.");
            return;
        }
        long from_id = a2.getId();
        long to_id = a3.getId();
        Transaction t = new FlatAmountProvisionTransaction(from_id, to_id, "3.00$", "3.00$");
        b1.makeTransaction(t);
        if (b1.equals(b5)) {
            System.out.println("Your bank equals method do not work properly.");
            return;
        }
        b5.makeTransaction(t);
        if (!b1.equals(b5)) {
            System.out.println("Your bank equals method do not work properly.");
            return;
        }
        System.out.println("All your equals methods work properly.");
    }

    private static void testTypicalUsage(Scanner jin) {
        String bank_name = jin.nextLine();
        int num_accounts = jin.nextInt();
        jin.nextLine();
        Account accounts[] = new Account[num_accounts];
        for (int i = 0; i < num_accounts; ++i)
            accounts[i] = new Account(jin.nextLine(), jin.nextLine());
        Bank bank = new Bank(bank_name, accounts);
        while (true) {
            String line = jin.nextLine();
            switch (line) {
                case "stop":
                    return;
                case "transaction":
                    String descrption = jin.nextLine();
                    String amount = jin.nextLine();
                    String parameter = jin.nextLine();
                    int from_idx = jin.nextInt();
                    int to_idx = jin.nextInt();
                    jin.nextLine();
                    Transaction t = getTransaction(descrption, from_idx, to_idx, amount, parameter, bank);
                    System.out.println("Transaction amount: " + t.getAmount());
                    System.out.println("Transaction description: " + t.getDescription());
                    System.out.println("Transaction successful? " + bank.makeTransaction(t));
                    break;
                case "print":
                    System.out.println(bank.toString());
                    System.out.println("Total provisions: " + bank.totalProvision());
                    System.out.println("Total transfers: " + bank.totalTransfers());
                    System.out.println();
                    break;
            }
        }
    }

    private static Transaction getTransaction(String description, int from_idx, int to_idx, String amount, String o, Bank bank) {
        switch (description) {
            case "FlatAmount":
                return new FlatAmountProvisionTransaction(bank.getAccounts()[from_idx].getId(),
                        bank.getAccounts()[to_idx].getId(), amount, o);
            case "FlatPercent":
                return new FlatPercentProvisionTransaction(bank.getAccounts()[from_idx].getId(),
                        bank.getAccounts()[to_idx].getId(), amount, Integer.parseInt(o));
        }
        return null;
    }


}
